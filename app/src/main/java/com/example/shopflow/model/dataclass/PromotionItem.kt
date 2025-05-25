package com.example.shopflow.model.dataclass

import androidx.compose.ui.graphics.Color

/**
 * Represents a promotion item displayed in a banner.
 *
 * @property id Unique identifier for the promotion item. Defaults to 0.
 * @property title The main title of the promotion. Defaults to an empty string.
 * @property subtitle A subtitle or additional information for the promotion. Defaults to an empty string.
 * @property dateRange The validity period of the promotion. Defaults to an empty string.
 * @property productImageResId The resource ID for the product image associated with the promotion.
 * @property backgroundColor The background color for the promotion item. Defaults to [Color.Transparent].
 */
data class PromotionItem(
    val id: Int = 0,
    val title: String = "",
    val subtitle: String = "",
    val dateRange: String = "",
    val productImageResId: Int, // Product image
    val backgroundColor: Color = Color.Transparent // Default background
)