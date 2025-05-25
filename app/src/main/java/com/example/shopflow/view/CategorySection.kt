package com.example.shopflow.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopflow.R
import com.example.shopflow.debug.sampleCategories
import com.example.shopflow.model.dataclass.ProductCategory
import com.example.shopflow.ui.theme.PrimaryContainerColor
import com.example.shopflow.ui.theme.PrimaryTextColorOnSurfaceColor
import com.example.shopflow.ui.theme.centuryOldStyleStdBoldFamily
import com.example.shopflow.ui.theme.neuzeitSBookFamily
import com.example.shopflow.ui.theme.neuzeitSltStdBookFamily

/**
 * A composable function that displays a section of product categories.
 *
 * This function takes a list of [ProductCategory] objects and displays them in a horizontally scrollable list.
 * It also includes a header with a title and a "See All" button.
 * If the list of categories is empty, it displays a message indicating that there are no categories.
 *
 * @param modifier The modifier to be applied to the CategorySection.
 * @param categories The list of [ProductCategory] objects to display.
 * @param onCategoryClick A lambda function that is called when a category is clicked.
 * @param categorySectionHeaderTitle The resource ID for the title of the category section header.
 * @param seeAllCategoryButtonDescription The resource ID for the description of the "See All" button.
 * @param categoryHeaderFontFamily The font family to be used for the category section header title.
 * @param seeAllCategoryFontFamily The font family to be used for the "See All" button text.
 * @param categoryItemFontFamily The font family to be used for the category item text.
 * @param categoryHeaderFontColor The color of the category section header title.
 * @param seeAllCategoryFontColor The color of the "See All" button text.
 * @param categoryItemContainerColor The background color of the category item container.
 * @param categoryItemFontColor The color of the category item text.
 * @param onSeeAllCategoryClick A lambda function that is called when the "See All" button is clicked.
 */
@Composable
fun CategorySection(
    modifier: Modifier = Modifier,
    categories: List<ProductCategory>,
    onCategoryClick: (ProductCategory) -> Unit,
    categorySectionHeaderTitle: Int = R.string.category_section_title,
    seeAllCategoryButtonDescription: Int = R.string.see_all_categories_button_description,
    categoryHeaderFontFamily: FontFamily = centuryOldStyleStdBoldFamily,
    seeAllCategoryFontFamily: FontFamily = neuzeitSBookFamily,
    categoryItemFontFamily: FontFamily = neuzeitSltStdBookFamily,
    categoryHeaderFontColor: Color = PrimaryTextColorOnSurfaceColor,
    seeAllCategoryFontColor: Color = PrimaryTextColorOnSurfaceColor,
    categoryItemContainerColor: Color = PrimaryContainerColor,
    categoryItemFontColor: Color = PrimaryTextColorOnSurfaceColor,
    onSeeAllCategoryClick: () -> Unit = {}
) {
    if (categories.isNotEmpty()) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            CategorySectionHeader(
                modifier = Modifier,
                categorySectionHeaderTitle = categorySectionHeaderTitle,
                categoryHeaderFontFamily = categoryHeaderFontFamily,
                seeAllCategoryFontFamily = seeAllCategoryFontFamily,
                categoryHeaderFontColor = categoryHeaderFontColor,
                seeAllCategoryFontColor = seeAllCategoryFontColor,
                onSeeAllCategoryClick = onSeeAllCategoryClick
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories, key = { it.id }) { category ->
                    CategoryChip(
                        modifier = Modifier,
                        category = category,
                        categoryItemFontFamily = categoryItemFontFamily,
                        categoryItemContainerColor = categoryItemContainerColor,
                        categoryItemFontColor = categoryItemFontColor,
                        onCategoryClick = onCategoryClick
                    )
                }
            }
        }
    } else {
        // If the categories list is empty
        Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(id = R.string.category_empty_state),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

/**
 * A composable function that displays the header for a category section.
 *
 * This function displays a title for the category section and a "See All" button.
 *
 * @param modifier The modifier to be applied to the CategorySectionHeader.
 * @param categorySectionHeaderTitle The resource ID for the title of the category section header.
 * @param categoryHeaderFontFamily The font family to be used for the category section header title.
 * @param seeAllCategoryFontFamily The font family to be used for the "See All" button text.
 * @param categoryHeaderFontColor The color of the category section header title.
 * @param seeAllCategoryFontColor The color of the "See All" button text.
 * @param onSeeAllCategoryClick A lambda function that is called when the "See All" button is clicked.
 */
@Composable
fun CategorySectionHeader(
    modifier: Modifier = Modifier,
    categorySectionHeaderTitle: Int = R.string.category_section_title,
    categoryHeaderFontFamily: FontFamily = centuryOldStyleStdBoldFamily,
    seeAllCategoryFontFamily: FontFamily = neuzeitSBookFamily,
    categoryHeaderFontColor: Color = PrimaryTextColorOnSurfaceColor,
    seeAllCategoryFontColor: Color = PrimaryTextColorOnSurfaceColor,
    onSeeAllCategoryClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = categorySectionHeaderTitle),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontFamily = categoryHeaderFontFamily, color = categoryHeaderFontColor
            )
        )
        TextButton(
            onClick = onSeeAllCategoryClick,
            colors = ButtonDefaults.textButtonColors(contentColor = seeAllCategoryFontColor)
        ) {
            Text(
                text = stringResource(id = R.string.see_all_categories_button_description),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.labelLarge.copy(
                    fontFamily = seeAllCategoryFontFamily, color = seeAllCategoryFontColor
                )
            )
        }
    }
}

/**
 * A composable function that displays a single category item as a chip.
 *
 * This function takes a [ProductCategory] object and displays its image and name.
 * The chip is clickable and calls the [onCategoryClick] lambda when clicked.
 *
 * @param modifier The modifier to be applied to the CategoryChip.
 * @param category The [ProductCategory] object to display.
 * @param categoryItemFontFamily The font family to be used for the category item text.
 * @param categoryItemContainerColor The background color of the category item container.
 * @param categoryItemFontColor The color of the category item text.
 * @param onCategoryClick A lambda function that is called when the category chip is clicked.
 */
@Composable
fun CategoryChip(
    modifier: Modifier = Modifier,
    category: ProductCategory,
    categoryItemFontFamily: FontFamily = neuzeitSltStdBookFamily,
    categoryItemContainerColor: Color = PrimaryContainerColor,
    categoryItemFontColor: Color = PrimaryTextColorOnSurfaceColor,
    onCategoryClick: (ProductCategory) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = Modifier.clickable { onCategoryClick(category) },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CategoryImage(
                modifier = Modifier.size(100.dp),
                category = category,
                categoryItemContainerColor = categoryItemContainerColor
            )
            Text(
                text = category.name,
                modifier = Modifier.padding(4.dp),
                style = MaterialTheme.typography.labelLarge.copy(
                    fontFamily = categoryItemFontFamily, color = categoryItemFontColor
                )
            )
        }
    }
}

/**
 * A composable function that displays the image for a product category.
 *
 * This function displays the image of a [ProductCategory] within a circular container.
 *
 * @param modifier The modifier to be applied to the CategoryImage.
 * @param category The [ProductCategory] object whose image is to be displayed.
 * @param categoryItemContainerColor The background color of the circular container holding the image.
 */
@Composable
fun CategoryImage(
    modifier: Modifier = Modifier,
    category: ProductCategory,
    categoryItemContainerColor: Color = PrimaryContainerColor
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = categoryItemContainerColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = category.productImage),
            contentDescription = category.name,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = false, name = "Category Section Header")
@Composable
fun CategorySectionHeaderPreview() {
    CategorySectionHeader(onSeeAllCategoryClick = {})
}

@Preview(showBackground = false, name = "Category Section")
@Composable
fun CategorySectionPreview() {
    CategorySection(categories = sampleCategories, onCategoryClick = {})
}

@Preview(showBackground = false, name = "Category Section when List is empty")
@Composable
fun CategorySectionListEmptyPreview() {
    CategorySection(categories = emptyList(), onCategoryClick = {})
}