package com.example.shopflow.debug

import com.example.shopflow.R
import com.example.shopflow.model.dataclass.PromotionItem

/**
 * A sample list of [PromotionItem]s for debugging and demonstration purposes.
 * This list is used to populate UI elements that display promotions when actual data is not available.
 */
val samplePromotions = listOf(
    PromotionItem(
        id = 1,
        title = "GET 20% OFF",
        subtitle = "Get 20% off",
        dateRange = "12-16 October",
        productImageResId = R.drawable.product_image,
    ), PromotionItem(
        id = 2,
        title = "SALE ENDS SOON",
        subtitle = "Limited time offer!",
        dateRange = "Ends 20 Oct",
        productImageResId = R.drawable.categorysample,
    ), PromotionItem(
        id = 3,
        title = "FREE SHIPPING",
        subtitle = "On orders over Rs.200",
        dateRange = "This Week Only",
        productImageResId = R.drawable.product_image,
    )
)