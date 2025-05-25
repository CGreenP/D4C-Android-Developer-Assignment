package com.example.shopflow.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shopflow.debug.sampleCategories
import com.example.shopflow.debug.sampleProducts
import com.example.shopflow.debug.samplePromotions
import com.example.shopflow.model.dataclass.Product
import com.example.shopflow.model.dataclass.ProductCategory
import com.example.shopflow.model.dataclass.PromotionItem

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

        }
    }
}