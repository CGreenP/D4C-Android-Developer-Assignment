package com.example.shopflow.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopflow.R
import com.example.shopflow.debug.sampleCategories
import com.example.shopflow.debug.sampleProducts
import com.example.shopflow.debug.samplePromotions
import com.example.shopflow.model.dataclass.Product
import com.example.shopflow.model.dataclass.ProductCategory
import com.example.shopflow.model.dataclass.PromotionItem
import com.example.shopflow.ui.theme.AccentGrey
import com.example.shopflow.ui.theme.AccentPurple
import com.example.shopflow.ui.theme.AccentRed
import com.example.shopflow.ui.theme.AccentYellow
import com.example.shopflow.ui.theme.PrimaryContainerColor
import com.example.shopflow.ui.theme.PrimaryGreen
import com.example.shopflow.ui.theme.PrimaryTextColorOnSurfaceColor
import com.example.shopflow.ui.theme.SecondaryContainerColor
import com.example.shopflow.ui.theme.SecondaryTextColorOnPrimaryGreen
import com.example.shopflow.ui.theme.centuryOldStyleStdBoldFamily
import com.example.shopflow.ui.theme.neuzeitSBookFamily
import com.example.shopflow.ui.theme.neuzeitSltStdBookFamily
import com.example.shopflow.ui.theme.tangarineFamily

/**
 * Composable function that represents the main content of the shop screen.
 * It displays promotions, categories, and products in a lazy vertical grid.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param promotions A list of [PromotionItem] to be displayed in the promotion banner. Defaults to [samplePromotions].
 * @param categories A list of [ProductCategory] to be displayed in the category section. Defaults to [sampleCategories].
 * @param products A list of [Product] to be displayed in the product section. Defaults to [sampleProducts].
 * @param onBannerClick A callback function invoked when a promotion banner is clicked. It receives the clicked [PromotionItem].
 * @param onCategoryClick A callback function invoked when a product category is clicked. It receives the clicked [ProductCategory].
 * @param onSeeAllCategoryClick A callback function invoked when the "See All" button for categories is clicked.
 * @param onProductClick A callback function invoked when a product card is clicked. It receives the clicked [Product].
 * @param onSeeAllProductClick A callback function invoked when the "See All" button for products is clicked.
 * @param onAddToFavouriteClick A callback function invoked when the "Add to Favourite" button on a product card is clicked. It receives the [Product].
 * @param onAddToCartClick A callback function invoked when the "Add to Cart" button on a product card is clicked. It receives the [Product].
 * @param onReviewClick A callback function invoked when the review section of a product card is clicked. It receives the [Product].
 */
@Composable
fun ShopScreenContent(
    modifier: Modifier = Modifier,
    promotions: List<PromotionItem> = samplePromotions,
    categories: List<ProductCategory> = sampleCategories,
    products: List<Product> = sampleProducts,
    onBannerClick: (PromotionItem) -> Unit = {},
    onCategoryClick: (ProductCategory) -> Unit = {},
    onSeeAllCategoryClick: () -> Unit = {},
    onProductClick: (Product) -> Unit = {},
    onSeeAllProductClick: () -> Unit = {},
    onAddToFavouriteClick: (Product) -> Unit = {},
    onAddToCartClick: (Product) -> Unit = {},
    onReviewClick: (Product) -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(), columns = GridCells.Adaptive(400.dp)
        ) {
            // Promotion Banner
            item(
                span = { GridItemSpan(maxLineSpan) }) {
                PromotionBanner(
                    modifier = Modifier,
                    promotions = promotions,
                    autoCycleDelayMillis = 5000L,
                    bannerContainer = R.drawable.banner_card,
                    bannerHeadlineFontFamily = neuzeitSBookFamily,
                    bannerSubtitleFontFamily = neuzeitSltStdBookFamily,
                    bannerDateRangeFontFamily = neuzeitSBookFamily,
                    bannerContainerColor = PrimaryContainerColor,
                    bannerContentColor = PrimaryTextColorOnSurfaceColor,
                    bannerDateRangeContainerColor = PrimaryGreen,
                    bannerDateRangeContentColor = SecondaryTextColorOnPrimaryGreen,
                    bottomIndicatorPrimaryContainerColor = PrimaryGreen,
                    bottomIndicatorSurfaceVariantColor = PrimaryContainerColor,
                    onBannerClick = { onBannerClick(it) },
                )
            }
            // Category Section
            item(
                span = { GridItemSpan(maxLineSpan) }) {
                CategorySection(
                    modifier = Modifier,
                    categories = categories,
                    categorySectionHeaderTitle = R.string.category_section_title,
                    seeAllCategoryButtonDescription = R.string.see_all_categories_button_description,
                    categoryHeaderFontFamily = centuryOldStyleStdBoldFamily,
                    seeAllCategoryFontFamily = neuzeitSBookFamily,
                    categoryItemFontFamily = neuzeitSltStdBookFamily,
                    categoryHeaderFontColor = PrimaryTextColorOnSurfaceColor,
                    seeAllCategoryFontColor = PrimaryTextColorOnSurfaceColor,
                    categoryItemContainerColor = PrimaryContainerColor,
                    categoryItemFontColor = PrimaryTextColorOnSurfaceColor,
                    onCategoryClick = { onCategoryClick(it) },
                    onSeeAllCategoryClick = { onSeeAllCategoryClick() },
                )
            }
            // Product Section
            item(
                span = { GridItemSpan(maxLineSpan) }) {
                ProductSectionHeader(
                    modifier = Modifier,
                    productSectionTitle = R.string.product_section_title,
                    seeAllProductButtonDescription = R.string.see_all_products_button_description,
                    productSectionTitleFontFamily = centuryOldStyleStdBoldFamily,
                    seeAllProductFontFamily = neuzeitSBookFamily,
                    productSectionTitleFontColor = PrimaryTextColorOnSurfaceColor,
                    seeAllProductFontColor = PrimaryTextColorOnSurfaceColor,
                    onSeeAllProductClick = { onSeeAllProductClick() },
                )
            }
            items(products, key = { it.id }) { product ->
                ProductCard(
                    modifier = Modifier,
                    product = product,
                    productCardHeight = 512.dp,
                    cardBackgroundImage = R.drawable.product_bg_card,
                    productDescriptionBackgroundImage = R.drawable.product_title_card,
                    productDescriptionHeight = 216.dp,
                    favouriteIconUnselected = Icons.Outlined.FavoriteBorder,
                    favouriteIconSelected = Icons.Filled.Favorite,
                    shoppingCartIconAddToCart = Icons.Filled.AddShoppingCart,
                    shoppingCartIconRemoveFromCart = Icons.Filled.RemoveShoppingCart,
                    favouriteIconButtonSelectedDescription = R.string.favourite_button_selected_description,
                    favouriteIconButtonUnselectedDescription = R.string.favourite_button_unselected_description,
                    tagFontFamily = neuzeitSBookFamily,
                    productNameFontFamily = tangarineFamily,
                    productStockIndicatorFontFamily = neuzeitSBookFamily,
                    productDescriptionFontFamily = neuzeitSltStdBookFamily,
                    productCategoryFontFamily = neuzeitSBookFamily,
                    productSellingPriceFontFamily = neuzeitSltStdBookFamily,
                    productActualPriceFontFamily = neuzeitSltStdBookFamily,
                    productReviewFontFamily = neuzeitSBookFamily,
                    shoppingCartIconButtonAddToCartDescription = R.string.shopping_cart_button_add_to_cart_description,
                    shoppingCartIconButtonRemoveFromCartDescription = R.string.shopping_cart_button_remove_from_cart_description,
                    productInStockString = R.string.product_in_stock_state,
                    productOutOfStockString = R.string.product_out_of_stock_state,
                    fullReviewStarDescription = R.string.filled_star_description,
                    halfReviewStarDescription = R.string.half_star_description,
                    emptyReviewStarDescription = R.string.outlined_star_description,
                    favouriteIconButtonColor = SecondaryContainerColor,
                    favouriteIconUnselectedColor = PrimaryTextColorOnSurfaceColor,
                    favouriteIconSelectedColor = AccentPurple,
                    tagContainerColor = PrimaryContainerColor,
                    tagTextColor = PrimaryGreen,
                    shoppingCartIconAddToCartColor = PrimaryGreen,
                    shoppingCartIconRemoveFromCartColor = AccentRed,
                    shoppingCartIconDisabledColor = AccentGrey,
                    productNameTextColor = PrimaryGreen,
                    productInStockIndicatorColor = PrimaryGreen,
                    productOutOfStockIndicatorColor = AccentRed,
                    productDescriptionTextColor = PrimaryTextColorOnSurfaceColor,
                    productSellingPriceTextColor = AccentPurple,
                    productActualPriceTextColor = AccentGrey,
                    reviewStarColor = AccentYellow,
                    productReviewTextColor = PrimaryTextColorOnSurfaceColor,
                    onProductClick = { onProductClick(it) },
                    onAddToFavouriteClick = { onAddToFavouriteClick(it) },
                    onAddToCartClick = { onAddToCartClick(it) },
                    onReviewClick = { onReviewClick(it) })
            }
            item(
                span = { GridItemSpan(maxLineSpan) }) {
                // If the categories list is empty
                if (products.isEmpty()) {
                    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text(
                            text = stringResource(id = R.string.product_empty_state),
                            modifier = Modifier.padding(16.dp),
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false, name = "Shop Screen Content")
@Composable
fun ShopScreenContentPreview() {
    ShopScreenContent()
}