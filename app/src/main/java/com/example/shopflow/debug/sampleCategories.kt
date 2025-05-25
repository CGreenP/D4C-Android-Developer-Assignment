package com.example.shopflow.debug

import com.example.shopflow.R
import com.example.shopflow.model.dataclass.ProductCategory

/**
 * A list of sample product categories for debugging purposes.
 * Each category has an ID, a name, and a drawable resource ID for its image.
 */
val sampleCategories = listOf(
    ProductCategory(1, "Cleansers", R.drawable.categorysample),
    ProductCategory(2, "Serums", R.drawable.categorysample),
    ProductCategory(3, "Moisturizers", R.drawable.product_image),
    ProductCategory(4, "Sunscreens", R.drawable.categorysample),
    ProductCategory(5, "Masks", R.drawable.product_image)
)