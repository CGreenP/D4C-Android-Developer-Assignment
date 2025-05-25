package com.example.shopflow.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopflow.R
import com.example.shopflow.debug.sampleCategories
import com.example.shopflow.debug.sampleProducts
import com.example.shopflow.debug.samplePromotions
import com.example.shopflow.model.dataclass.Product
import com.example.shopflow.model.dataclass.ProductCategory
import com.example.shopflow.model.dataclass.PromotionItem
import com.example.shopflow.ui.theme.PrimaryContainerColor
import com.example.shopflow.ui.theme.PrimaryGreen
import com.example.shopflow.ui.theme.PrimaryTextColorOnSurfaceColor
import com.example.shopflow.ui.theme.SurfaceColor
import com.example.shopflow.ui.theme.neuzeitSBookFamily
import com.example.shopflow.ui.theme.neuzeitSltStdBookFamily

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
                    bannerDateRangeContentColor = SurfaceColor,
                    bottomIndicatorPrimaryContainerColor = PrimaryGreen,
                    bottomIndicatorSurfaceVariantColor = PrimaryContainerColor,
                    onBannerClick = { onBannerClick(it) },

                    )
            }
        }
    }
}

@Preview(showBackground = true, name = "Shop Screen Content")
@Composable
fun ShopScreenContentPreview() {
    ShopScreenContent()
}