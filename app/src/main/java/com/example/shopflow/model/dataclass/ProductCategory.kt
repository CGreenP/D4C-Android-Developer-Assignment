package com.example.shopflow.model.dataclass

/**
 * Represents a category of products.
 *
 * @property id The unique identifier for the product category. Defaults to 0.
 * @property name The name of the product category. Defaults to an empty string.
 * @property productImage A placeholder for the product category's image.
 *                      This is ideally an integer representing a drawable resource ID.
 */
data class ProductCategory(
    val id: Int = 0,
    val name: String = "",
    val productImage: Int // Placeholder for product image - ideally a drawable resource
)