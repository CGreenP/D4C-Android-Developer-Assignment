package com.example.shopflow.model.dataclass

/**
 * Represents a product in the shop.
 *
 * @property id The unique identifier for the product. Defaults to 0.
 * @property name The name of the product. Defaults to an empty string.
 * @property description A detailed description of the product. Defaults to an empty string.
 * @property actualPrice The original price of the product. Defaults to 0.00.
 * @property sellingPrice The price at which the product is being sold (could be discounted). Defaults to 0.00.
 * @property imageUrl The resource ID for the product's image. In a real app, this might be a URL string or a drawable resource ID.
 * @property category The category the product belongs to. Defaults to an empty string.
 * @property rating The average customer rating of the product. Defaults to 0.0f.
 * @property reviewCount The total number of reviews for the product. Defaults to 0.
 * @property stockStatus Indicates whether the product is in stock. Defaults to true.
 * @property isFavourite Indicates whether the user has marked this product as a favorite. Defaults to false.
 * @property isAddedToCart Indicates whether the product has been added to the user's cart. Defaults to false.
 * @property tag A tag associated with the product (e.g., "New", "Sale"). Defaults to an empty string.
 */
data class Product(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val actualPrice: Double = 0.00,
    val sellingPrice: Double = 0.00,
    val imageUrl: Int, // In a real app, this might be a URL or a drawable resource ID
    val category: String = "",
    val rating: Float = 0f,
    val reviewCount: Int = 0,
    val stockStatus: Boolean = true,
    val isFavourite: Boolean = false,
    val isAddedToCart: Boolean = false,
    val tag: String = ""
)