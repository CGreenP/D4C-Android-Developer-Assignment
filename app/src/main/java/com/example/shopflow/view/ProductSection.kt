package com.example.shopflow.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.shopflow.R
import com.example.shopflow.model.dataclass.Product
import com.example.shopflow.ui.theme.AccentGrey
import com.example.shopflow.ui.theme.AccentPurple
import com.example.shopflow.ui.theme.AccentRed
import com.example.shopflow.ui.theme.AccentYellow
import com.example.shopflow.ui.theme.PrimaryContainerColor
import com.example.shopflow.ui.theme.PrimaryGreen
import com.example.shopflow.ui.theme.PrimaryTextColorOnSurfaceColor
import com.example.shopflow.ui.theme.SecondaryContainerColor
import com.example.shopflow.ui.theme.centuryOldStyleStdBoldFamily
import com.example.shopflow.ui.theme.neuzeitSBookFamily
import com.example.shopflow.ui.theme.neuzeitSltStdBookFamily
import com.example.shopflow.ui.theme.tangarineFamily
import kotlin.math.ceil
import kotlin.math.floor

/**
 * A composable function that displays the header for a product section.
 *
 * This function displays the title of the product section and a "See All" button.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param productSectionTitle The resource ID for the title of the product section.
 * @param seeAllProductButtonDescription The resource ID for the description of the "See All" button.
 * @param productSectionTitleFontFamily The font family to be used for the product section title.
 * @param seeAllProductFontFamily The font family to be used for the "See All" button text.
 * @param productSectionTitleFontColor The color of the product section title.
 * @param seeAllProductFontColor The color of the "See All" button text.
 * @param onSeeAllProductClick A lambda function to be executed when the "See All" button is clicked.
 */
@Composable
fun ProductSectionHeader(
    modifier: Modifier = Modifier,
    productSectionTitle: Int = R.string.product_section_title,
    seeAllProductButtonDescription: Int = R.string.see_all_products_button_description,
    productSectionTitleFontFamily: FontFamily = centuryOldStyleStdBoldFamily,
    seeAllProductFontFamily: FontFamily = neuzeitSBookFamily,
    productSectionTitleFontColor: Color = PrimaryTextColorOnSurfaceColor,
    seeAllProductFontColor: Color = PrimaryTextColorOnSurfaceColor,
    onSeeAllProductClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = productSectionTitle),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontFamily = productSectionTitleFontFamily, color = productSectionTitleFontColor
            )
        )
        TextButton(
            onClick = onSeeAllProductClick,
            colors = ButtonDefaults.textButtonColors(contentColor = seeAllProductFontColor)
        ) {
            Text(
                text = stringResource(id = seeAllProductButtonDescription),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontFamily = seeAllProductFontFamily, color = seeAllProductFontColor
                )
            )
        }
    }
}

/**
 * A composable function that displays a product card with its details.
 *
 * This function displays the product's image, name, description, price, rating,
 * and options to add to favorites and cart.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param product The [Product] object containing the product details.
 * @param productCardHeight The height of the product card.
 * @param cardBackgroundImage The resource ID for the background image of the product card.
 * @param productDescriptionBackgroundImage The resource ID for the background image of the product description section.
 * @param productDescriptionHeight The height of the product description section.
 * @param favouriteIconUnselected The icon for the unselected favorite button.
 * @param favouriteIconSelected The icon for the selected favorite button.
 * @param shoppingCartIconAddToCart The icon for the add to cart button.
 * @param shoppingCartIconRemoveFromCart The icon for the remove from cart button.
 * @param favouriteIconButtonSelectedDescription The resource ID for the content description of the selected favorite button.
 * @param favouriteIconButtonUnselectedDescription The resource ID for the content description of the unselected favorite button.
 * @param tagFontFamily The font family for the product tag.
 * @param productNameFontFamily The font family for the product name.
 * @param productStockIndicatorFontFamily The font family for the product stock indicator.
 * @param productDescriptionFontFamily The font family for the product description.
 * @param productCategoryFontFamily The font family for the product category.
 * @param productSellingPriceFontFamily The font family for the product selling price.
 * @param productActualPriceFontFamily The font family for the product actual price.
 * @param productReviewFontFamily The font family for the product review text.
 * @param shoppingCartIconButtonAddToCartDescription The resource ID for the content description of the add to cart button.
 * @param shoppingCartIconButtonRemoveFromCartDescription The resource ID for the content description of the remove from cart button.
 * @param productInStockString The resource ID for the "in stock" string.
 * @param productOutOfStockString The resource ID for the "out of stock" string.
 * @param fullReviewStarDescription The resource ID for the content description of a full review star.
 */
@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product,
    productCardHeight: Dp = 512.dp,
    cardBackgroundImage: Int = R.drawable.product_bg_card,
    productDescriptionBackgroundImage: Int = R.drawable.product_title_card,
    productDescriptionHeight: Dp = 216.dp,
    favouriteIconUnselected: ImageVector = Icons.Outlined.FavoriteBorder,
    favouriteIconSelected: ImageVector = Icons.Filled.Favorite,
    shoppingCartIconAddToCart: ImageVector = Icons.Filled.AddShoppingCart,
    shoppingCartIconRemoveFromCart: ImageVector = Icons.Filled.RemoveShoppingCart,
    favouriteIconButtonSelectedDescription: Int = R.string.favourite_button_selected_description,
    favouriteIconButtonUnselectedDescription: Int = R.string.favourite_button_unselected_description,
    tagFontFamily: FontFamily = neuzeitSBookFamily,
    productNameFontFamily: FontFamily = tangarineFamily,
    productStockIndicatorFontFamily: FontFamily = neuzeitSBookFamily,
    productDescriptionFontFamily: FontFamily = neuzeitSltStdBookFamily,
    productCategoryFontFamily: FontFamily = neuzeitSBookFamily,
    productSellingPriceFontFamily: FontFamily = neuzeitSltStdBookFamily,
    productActualPriceFontFamily: FontFamily = neuzeitSltStdBookFamily,
    productReviewFontFamily: FontFamily = neuzeitSBookFamily,
    shoppingCartIconButtonAddToCartDescription: Int = R.string.shopping_cart_button_add_to_cart_description,
    shoppingCartIconButtonRemoveFromCartDescription: Int = R.string.shopping_cart_button_remove_from_cart_description,
    productInStockString: Int = R.string.product_in_stock_state,
    productOutOfStockString: Int = R.string.product_out_of_stock_state,
    fullReviewStarDescription: Int = R.string.filled_star_description,
    halfReviewStarDescription: Int = R.string.half_star_description,
    emptyReviewStarDescription: Int = R.string.outlined_star_description,
    favouriteIconButtonColor: Color = SecondaryContainerColor,
    favouriteIconUnselectedColor: Color = PrimaryTextColorOnSurfaceColor,
    favouriteIconSelectedColor: Color = AccentPurple,
    tagContainerColor: Color = PrimaryContainerColor,
    tagTextColor: Color = PrimaryGreen,
    shoppingCartIconAddToCartColor: Color = PrimaryGreen,
    shoppingCartIconRemoveFromCartColor: Color = AccentRed,
    shoppingCartIconDisabledColor: Color = AccentGrey,
    productNameTextColor: Color = PrimaryGreen,
    productInStockIndicatorColor: Color = PrimaryGreen,
    productOutOfStockIndicatorColor: Color = AccentRed,
    productDescriptionTextColor: Color = PrimaryTextColorOnSurfaceColor,
    productSellingPriceTextColor: Color = AccentPurple,
    productActualPriceTextColor: Color = AccentGrey,
    reviewStarColor: Color = AccentYellow,
    productReviewTextColor: Color = PrimaryTextColorOnSurfaceColor,
    onProductClick: (Product) -> Unit,
    onAddToFavouriteClick: (Product) -> Unit = {},
    onAddToCartClick: (Product) -> Unit = {},
    onReviewClick: (Product) -> Unit = {}
) {
    var isFavourite by remember { mutableStateOf(product.isFavourite) }
    var isAddedToCart by remember { mutableStateOf(product.isAddedToCart) }
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(productCardHeight),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onProductClick(product) }) {
            Image(
                painter = painterResource(id = cardBackgroundImage),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentScale = ContentScale.FillBounds
            )
            IconButton(
                onClick = {
                    isFavourite = !isFavourite
                    onAddToFavouriteClick(product)
                },
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.TopStart),
                colors = IconButtonDefaults.iconButtonColors(favouriteIconButtonColor)
            ) {
                if (isFavourite) {
                    Icon(
                        imageVector = favouriteIconSelected,
                        contentDescription = stringResource(id = favouriteIconButtonSelectedDescription),
                        tint = favouriteIconSelectedColor
                    )
                } else {
                    Icon(
                        imageVector = favouriteIconUnselected,
                        contentDescription = stringResource(id = favouriteIconButtonUnselectedDescription),
                        tint = favouriteIconUnselectedColor
                    )
                }
            }
            if (product.tag.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 28.dp, vertical = 16.dp)
                        .background(tagContainerColor, RoundedCornerShape(50))
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        text = product.tag,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontFamily = tagFontFamily,
                            color = tagTextColor,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
            Image(
                painter = painterResource(product.imageUrl),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .align(Alignment.Center)
                    .offset(y = (-80).dp),
                contentScale = ContentScale.Fit
            )
            ProductDescription(
                modifier = Modifier.align(Alignment.BottomCenter),
                product = product,
                productDescriptionBackgroundImage = productDescriptionBackgroundImage,
                productDescriptionHeight = productDescriptionHeight,
                productNameFontFamily = productNameFontFamily,
                productStockIndicatorFontFamily = productStockIndicatorFontFamily,
                productDescriptionFontFamily = productDescriptionFontFamily,
                productCategoryFontFamily = productCategoryFontFamily,
                productSellingPriceFontFamily = productSellingPriceFontFamily,
                productActualPriceFontFamily = productActualPriceFontFamily,
                productReviewFontFamily = productReviewFontFamily,
                productInStockString = productInStockString,
                productOutOfStockString = productOutOfStockString,
                fullReviewStarDescription = fullReviewStarDescription,
                halfReviewStarDescription = halfReviewStarDescription,
                emptyReviewStarDescription = emptyReviewStarDescription,
                productNameTextColor = productNameTextColor,
                productInStockIndicatorColor = productInStockIndicatorColor,
                productOutOfStockIndicatorColor = productOutOfStockIndicatorColor,
                productDescriptionTextColor = productDescriptionTextColor,
                productSellingPriceTextColor = productSellingPriceTextColor,
                productActualPriceTextColor = productActualPriceTextColor,
                reviewStarColor = reviewStarColor,
                productReviewTextColor = productReviewTextColor,
                onReviewClick = { onReviewClick(product) },
            )

            if (!isAddedToCart) {
                IconButton(
                    onClick = {
                        isAddedToCart = !isAddedToCart
                        onAddToCartClick(product)
                    },
                    enabled = product.stockStatus,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-24).dp, y = (-24).dp)
                        .border(
                            width = 2.dp,
                            color = if (product.stockStatus) shoppingCartIconAddToCartColor else shoppingCartIconDisabledColor,
                            shape = CircleShape
                        ),
                ) {
                    Icon(
                        imageVector = shoppingCartIconAddToCart,
                        contentDescription = stringResource(id = shoppingCartIconButtonAddToCartDescription),
                        tint = if (product.stockStatus) shoppingCartIconAddToCartColor else shoppingCartIconDisabledColor
                    )
                }
            } else {
                IconButton(
                    onClick = {
                        isAddedToCart = !isAddedToCart
                        onAddToCartClick(product)
                    },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = (-24).dp, y = (-24).dp)
                        .border(
                            width = 2.dp,
                            color = shoppingCartIconRemoveFromCartColor,
                            shape = CircleShape
                        ),
                ) {
                    Icon(
                        imageVector = shoppingCartIconRemoveFromCart,
                        contentDescription = stringResource(id = shoppingCartIconButtonRemoveFromCartDescription),
                        tint = shoppingCartIconRemoveFromCartColor
                    )
                }
            }
        }
    }
}

/**
 * A composable function that displays the description of a product.
 *
 * This function displays the product name, stock status, description, category, price, and rating.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param product The product object containing the details to be displayed.
 * @param productDescriptionBackgroundImage The resource ID for the background image of the product description.
 * @param productDescriptionHeight The height of the product description section.
 * @param productNameFontFamily The font family for the product name.
 * @param productStockIndicatorFontFamily The font family for the product stock indicator.
 * @param productDescriptionFontFamily The font family for the product description.
 * @param productCategoryFontFamily The font family for the product category.
 * @param productSellingPriceFontFamily The font family for the product selling price.
 * @param productActualPriceFontFamily The font family for the product actual price.
 * @param productReviewFontFamily The font family for the product review text.
 * @param productInStockString The resource ID for the "in stock" string.
 * @param productOutOfStockString The resource ID for the "out of stock" string.
 * @param fullReviewStarDescription The resource ID for the content description of a full review star.
 * @param halfReviewStarDescription The resource ID for the content description of a half review star.
 * @param emptyReviewStarDescription The resource ID for the content description of an empty review star.
 * @param productNameTextColor The color of the product name text.
 * @param productInStockIndicatorColor The color of the "in stock" indicator.
 * @param productOutOfStockIndicatorColor The color of the "out of stock" indicator.
 * @param productDescriptionTextColor The color of the product description text.
 * @param productSellingPriceTextColor The color of the product selling price text.
 * @param productActualPriceTextColor The color of the product actual price text.
 * @param reviewStarColor The color of the review stars.
 * @param productReviewTextColor The color of the product review text.
 * @param onReviewClick A lambda function to be executed when the review section is clicked.
 */
@Composable
fun ProductDescription(
    modifier: Modifier = Modifier,
    product: Product,
    productDescriptionBackgroundImage: Int = R.drawable.product_title_card,
    productDescriptionHeight: Dp = 216.dp,
    productNameFontFamily: FontFamily = tangarineFamily,
    productStockIndicatorFontFamily: FontFamily = neuzeitSBookFamily,
    productDescriptionFontFamily: FontFamily = neuzeitSltStdBookFamily,
    productCategoryFontFamily: FontFamily = neuzeitSBookFamily,
    productSellingPriceFontFamily: FontFamily = neuzeitSltStdBookFamily,
    productActualPriceFontFamily: FontFamily = neuzeitSltStdBookFamily,
    productReviewFontFamily: FontFamily = neuzeitSBookFamily,
    productInStockString: Int = R.string.product_in_stock_state,
    productOutOfStockString: Int = R.string.product_out_of_stock_state,
    fullReviewStarDescription: Int = R.string.filled_star_description,
    halfReviewStarDescription: Int = R.string.half_star_description,
    emptyReviewStarDescription: Int = R.string.outlined_star_description,
    productNameTextColor: Color = PrimaryGreen,
    productInStockIndicatorColor: Color = PrimaryGreen,
    productOutOfStockIndicatorColor: Color = AccentRed,
    productDescriptionTextColor: Color = PrimaryTextColorOnSurfaceColor,
    productSellingPriceTextColor: Color = AccentPurple,
    productActualPriceTextColor: Color = AccentGrey,
    reviewStarColor: Color = AccentYellow,
    productReviewTextColor: Color = PrimaryTextColorOnSurfaceColor,
    onReviewClick: () -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = productDescriptionBackgroundImage),
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(productDescriptionHeight)
                .padding(16.dp)
                .align(Alignment.BottomStart),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.name,
                    modifier = Modifier,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontFamily = productNameFontFamily, color = productNameTextColor
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    if (product.stockStatus) {
                        Icon(
                            imageVector = Icons.Filled.RadioButtonChecked,
                            contentDescription = stringResource(id = productInStockString),
                            tint = productInStockIndicatorColor,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = stringResource(id = productInStockString),
                            modifier = Modifier.padding(horizontal = 4.dp),
                            maxLines = 1,
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontFamily = productStockIndicatorFontFamily,
                                color = productInStockIndicatorColor
                            ),
                            overflow = TextOverflow.Ellipsis,
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.RadioButtonChecked,
                            contentDescription = stringResource(id = productOutOfStockString),
                            tint = productOutOfStockIndicatorColor,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = stringResource(id = productOutOfStockString),
                            modifier = Modifier.padding(horizontal = 4.dp),
                            maxLines = 1,
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontFamily = productStockIndicatorFontFamily,
                                color = productOutOfStockIndicatorColor
                            ),
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
            Text(
                text = product.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = productDescriptionFontFamily, color = productDescriptionTextColor
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = product.category,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = productCategoryFontFamily,
                    color = productDescriptionTextColor,
                    fontWeight = FontWeight.Bold
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Row {
                Text(
                    text = "RS. ${product.sellingPrice}",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontFamily = productSellingPriceFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = productSellingPriceTextColor
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "RS. ${product.actualPrice}",
                    modifier = Modifier,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontFamily = productActualPriceFontFamily,
                        color = productActualPriceTextColor,
                        textDecoration = TextDecoration.LineThrough
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = TextDecoration.LineThrough,
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Rating stars
                val rating = product.rating
                val maxRating = 5
                val filledStars = floor(rating).toInt()
                val halfStar = ceil(rating) - floor(rating) >= 0.5f
                val emptyStars = maxRating - filledStars - if (halfStar) 1 else 0

                // Filled stars
                repeat(filledStars) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = stringResource(id = fullReviewStarDescription),
                        tint = reviewStarColor,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Half star
                if (halfStar) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.StarHalf,
                        contentDescription = stringResource(id = halfReviewStarDescription),
                        tint = reviewStarColor,
                        modifier = Modifier.size(18.dp)
                    )
                }

                // Empty stars
                repeat(emptyStars) {
                    Icon(
                        imageVector = Icons.Outlined.StarOutline,
                        contentDescription = stringResource(id = emptyReviewStarDescription),
                        tint = reviewStarColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
                // Rating value and count
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "(${product.rating})",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = productReviewFontFamily,
                            color = productReviewTextColor,
                        ),
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    Text(
                        text = "${product.reviewCount} reviews",
                        textDecoration = TextDecoration.Underline,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = productReviewFontFamily,
                            color = productReviewTextColor,
                        ),
                        modifier = Modifier.clickable {
                            onReviewClick()
                        })

                }
            }
        }
    }
}

@Preview(showBackground = false, name = "Product Section Header")
@Composable
fun ProductSectionHeaderPreview() {
    ProductSectionHeader(onSeeAllProductClick = {})
}

@Preview(showBackground = false, name = "Product Card")
@Composable
fun ProductCardPreview() {
    val sampleProduct = Product(
        1,
        "Clarity Cleanser",
        "Gentle foam cleanser for all skin types. Removes impurities without drying.",
        444.00,
        355.20,
        R.drawable.product_image,
        "Cleansers",
        5f,
        249,
        true,
        true,
        false,
        "Best Seller"
    )
    ProductCard(
        product = sampleProduct,
        onProductClick = {},
        onAddToFavouriteClick = {},
        onAddToCartClick = {})
}

@Preview(showBackground = false, name = "Product Description")
@Composable
fun ProductDescriptionPreview() {
    val sampleProduct = Product(
        1,
        "Clarity Cleanser",
        "Gentle foam cleanser for all skin types. Removes impurities without drying.",
        444.00,
        355.20,
        R.drawable.product_image,
        "Cleansers",
        5f,
        249,
        true,
        true,
        false,
        "Best Seller"
    )
    ProductDescription(product = sampleProduct)
}