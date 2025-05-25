package com.example.shopflow.model.dataclass

/**
 * Represents the UI state for the main shopping screen.
 *
 * This data class holds all the necessary information to render the main screen,
 * including lists of promotions, product categories, and products. It also tracks
 * the count of favorite items and items in the cart, loading state, and any
 * error messages that need to be displayed to the user.
 *
 * @property promotions A list of [PromotionItem] objects to be displayed. Defaults to an empty list.
 * @property categories A list of [ProductCategory] objects representing available product categories. Defaults to an empty list.
 * @property products A list of [Product] objects to be displayed. Defaults to an empty list.
 * @property favoriteCount The total number of products marked as favorite by the user. Defaults to 0.
 * @property cartItemCount The total number of items currently in the user's shopping cart. Defaults to 0.
 * @property isLoading A boolean flag indicating whether the screen is currently loading data. `true` if loading, `false` otherwise. Defaults to `false`.
 * @property errorMessages A list of strings representing error messages to be displayed to the user. Defaults to an empty list.
 */
data class MainScreenUiState(
    val promotions: List<PromotionItem> = emptyList(),
    val categories: List<ProductCategory> = emptyList(),
    val products: List<Product> = emptyList(),
    val favoriteCount: Int = 0,
    val cartItemCount: Int = 0,
    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList() // For displaying errors
)