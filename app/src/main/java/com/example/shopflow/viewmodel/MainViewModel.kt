package com.example.shopflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopflow.debug.sampleCategories
import com.example.shopflow.debug.sampleProducts
import com.example.shopflow.debug.samplePromotions
import com.example.shopflow.model.dataclass.MainScreenUiState
import com.example.shopflow.model.dataclass.Product
import com.example.shopflow.model.dataclass.ProductCategory
import com.example.shopflow.model.dataclass.PromotionItem
import com.example.shopflow.model.dataclass.UserMessage
import com.example.shopflow.model.dataclass.UserMessage.ToastMessage
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the main screen of the application.
 *
 * This ViewModel is responsible for preparing and managing the data for the [MainScreenUiState]
 * and handling user interactions. It exposes UI state via [uiState] as a [StateFlow] and
 * one-time user messages (like toasts or snackbars) via [userMessages] as a [SharedFlow].
 *
 * Upon initialization, it loads initial data (simulated for now) which includes promotions,
 * categories, products, and initial counts for favorites and cart items.
 *
 * It provides functions to handle various user actions such as:
 * - Clicking on a product ([onProductClicked])
 * - Toggling a product's favorite status ([onToggleFavorite])
 * - Toggling a product's cart status ([onToggleCart])
 * - Clicking on a promotion banner ([onPromotionBannerClicked])
 * - Clicking on a product category ([onCategoryClicked])
 * - Clicking "See All" for categories or products ([onSeeAllCategoriesClicked], [onSeeAllProductsClicked])
 * - Handling top bar actions like back, search, favorite, and cart ([onBackClicked], [onSearchClicked], [onTopBarFavoriteClicked], [onTopBarCartClicked])
 * - Clicking on product reviews ([onReviewClicked])
 *
 * In a real application, data fetching would involve repositories interacting with a backend or local database.
 * User interactions that modify data (like adding to favorites or cart) would also trigger updates
 * to these backend/database systems.
 */
class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState(isLoading = true))
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    private val _userMessages = MutableSharedFlow<UserMessage>() // For one-time events
    val userMessages: SharedFlow<UserMessage> = _userMessages.asSharedFlow()

    // In a real app, these would come from a UserRepository, CartRepository, etc.
    private val _favoriteCount = MutableStateFlow(0) // Example
    // val favoriteCount: StateFlow<Int> = _favoriteCount.asStateFlow() // Expose if needed directly

    private val _cartItemCount = MutableStateFlow(0) // Example
    // val cartItemCount: StateFlow<Int> = _cartItemCount.asStateFlow() // Expose if needed directly

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        // Simulate fetching data
        viewModelScope.launch {
            // In a real app, you would fetch data from a repository (network/database)
            // For now, we use the sample data
            _uiState.update {
                it.copy(
                    promotions = samplePromotions,
                    categories = sampleCategories,
                    products = sampleProducts,
                    favoriteCount = calculateInitialFavoriteCount(sampleProducts),
                    cartItemCount = calculateInitialCartItemCount(sampleProducts),
                    isLoading = false
                )
            }
        }
    }

    private fun calculateInitialFavoriteCount(products: List<Product>): Int {
        return products.count { it.isFavourite }
    }

    private fun calculateInitialCartItemCount(products: List<Product>): Int {
        return products.count { it.isAddedToCart }
    }

    fun onProductClicked(product: Product) {/*TODO (Handle product click)*/
        // Handle product click, e.g., navigate to product detail screen
        println("Product clicked: ${product.name}")
        viewModelScope.launch {
            _userMessages.emit(ToastMessage("Product clicked: ${product.name}"))
        }
    }

    fun onToggleFavorite(productClicked: Product) {/*TODO (Handle favourite click)*/
        var message = ""
        _uiState.update { currentState ->
            val updatedProducts = currentState.products.map { product ->
                if (product.id == productClicked.id) {
                    val newFavoriteState = !product.isFavourite
                    message = if (newFavoriteState) {
                        "${product.name} added to favorites"
                    } else {
                        "${product.name} removed from favorites"
                    }
                    product.copy(isFavourite = newFavoriteState)
                } else {
                    product
                }
            }
            currentState.copy(
                products = updatedProducts,
                favoriteCount = updatedProducts.count { it.isFavourite })
        }
        viewModelScope.launch {
            if (message.isNotEmpty()) {
                _userMessages.emit(ToastMessage(message))
            }
        }
        // In a real app, also update this in our backend/database
        val product = _uiState.value.products.find { it.id == productClicked.id }
        if (product != null) {
            println("Toggled favorite for ${product.name}, isFavorite: ${product.isFavourite}")
        }
    }

    fun onToggleCart(productClicked: Product) {/*TODO (Handle cart click)*/
        var message = ""
        _uiState.update { currentState ->
            val updatedProducts = currentState.products.map { product ->
                if (product.id == productClicked.id) {
                    val newCartState = !product.isAddedToCart
                    // We might want to check stockStatus here before allowing add to cart
                    message = if (newCartState) {
                        "${product.name} added to cart"
                    } else {
                        "${product.name} removed from cart"
                    }
                    product.copy(isAddedToCart = newCartState)
                } else {
                    product
                }
            }
            currentState.copy(
                products = updatedProducts,
                cartItemCount = updatedProducts.count { it.isAddedToCart })
        }
        viewModelScope.launch {
            if (message.isNotEmpty()) {
                _userMessages.emit(ToastMessage(message))
            }
        }
        // In a real app, also update this in our backend/database
        val product = _uiState.value.products.find { it.id == productClicked.id }
        if (product != null) {
            println("Toggled cart for ${product.name}, isAddedToCart: ${product.isAddedToCart}")
        }
    }

    fun onPromotionBannerClicked(promotionItem: PromotionItem) {/*TODO (Handle banner click)*/
        println("Promotion banner clicked: ${promotionItem.title}")
        viewModelScope.launch {
            _userMessages.emit(ToastMessage("Banner clicked: ${promotionItem.title}"))
        }
        // Handle banner click, e.g., navigate or show details
    }

    fun onCategoryClicked(category: ProductCategory) {/*TODO (Handle category click)*/
        println("Category clicked: ${category.name}")
        viewModelScope.launch {
            _userMessages.emit(ToastMessage("Category clicked: ${category.name}"))
        }
        // Handle category click, e.g., filter products or navigate
    }

    fun onSeeAllCategoriesClicked() {/*TODO (Handle see all category click)*/
        println("See all categories clicked")
        viewModelScope.launch {
            _userMessages.emit(ToastMessage("See all categories clicked!"))
        }
        // Handle see all categories
    }

    fun onSeeAllProductsClicked() {/*TODO (Handle see all product click)*/
        println("See all products clicked")
        viewModelScope.launch {
            _userMessages.emit(ToastMessage("See all products clicked!"))
        }
        // Handle see all products
    }

    fun onBackClicked() {/*TODO (Handle back click)*/
        println("Back button clicked")
        viewModelScope.launch {
            _userMessages.emit(ToastMessage("Back button clicked!"))
        }
        // Typically handled by Navigation Component
    }

    fun onSearchClicked() {/*TODO (Handle search click)*/
        println("Search clicked")
        viewModelScope.launch {
            _userMessages.emit(ToastMessage("Search initiated!"))
        }
        // Navigate to search screen or show search bar
    }

    fun onTopBarFavoriteClicked() {/*TODO (Handle favourite click)*/
        println("Top bar favorite icon clicked")
        viewModelScope.launch {
            _userMessages.emit(ToastMessage("Favorites icon clicked!"))
        }
        // Navigate to favorites screen
    }

    fun onTopBarCartClicked() {/*TODO (Handle cart click)*/
        println("Top bar cart icon clicked")
        viewModelScope.launch {
            _userMessages.emit(ToastMessage("Cart icon clicked!"))
        }
        // Navigate to cart screen
    }

    fun onReviewClicked(product: Product) {/*TODO (Handle review click)*/
        println("Review clicked for product: ${product.name}")
        viewModelScope.launch {
            _userMessages.emit(ToastMessage("Reviews for ${product.name} clicked!"))
        }
        // Navigate to reviews for this product
    }
}