# D4C Android Developer Assignment
Task: Shop Flow Screen (Task Link: https://sites.google.com/view/task-d4c)
- Design one screen showing a Shop Flow screen for our mobile app.
- Include at least 2 products visible on the screen.
- Layout should reflect real usability, keeping in mind scrolling behavior.
- Use Jetpack Compose (with Kotlin) for implementation.

Design Assets available at: https://drive.google.com/drive/folders/1UOPBfSn3YqjpVXuYcjkXr6ApSGtaoPjG?usp=sharing
- App brand references
- Sample layout references
- Images and product assets

You may optionally use a Figma-to-Compose approach if you’re familiar with design tools.

# ShopFlow Mobile App

## Project Overview
ShopFlow is an Android application designed to showcase a single, well-designed "Shop Flow" screen. The primary goal of this project was to demonstrate proficiency in creating a usable and aesthetically pleasing user interface for an e-commerce context using modern Android development practices, specifically Jetpack Compose with Kotlin.

The application features a main screen that displays promotional banners, product categories, and a list of products, all while considering real-world usability aspects like scrolling behavior.

## Screenshot
<p align="center">
<img src="https://github.com/user-attachments/assets/e4754b59-f96c-402b-a408-0c0f1e6ea02e" width="288">
<img src="https://github.com/user-attachments/assets/dffe4798-f420-4fe6-8f64-d365557ae886" width="288">
<img src="https://github.com/user-attachments/assets/c4f406b5-4d91-4d2d-a13e-6aa85cdd6e10" width="288">
<img src="https://github.com/user-attachments/assets/c5820a18-0f8e-472e-8a76-c91ba5bbe58c" width="288">
</p>


## Video
https://github.com/user-attachments/assets/8853db54-0616-444f-b78c-c7c69aac52d6

## Features
*   **Dynamic Main Screen**: Displays a variety of shopping elements including:
    *   **Promotional Banners**: Auto-cycling banners showcasing current deals and offers.
    *   **Product Categories**: A horizontally scrollable list of product categories for easy navigation.
    *   **Product Listings**: A grid displaying multiple products with essential details.
*   **Interactive Product Cards**: Each product card shows:
    *   Product image and name.
    *   Description and category.
    *   Actual and selling price.
    *   Stock status.
    *   Customer rating and review count.
    *   Buttons to add to favorites and cart.
*   **Top App Bar**:
    *   Displays the screen title.
    *   Includes action icons for Search, Favorites, and Cart.
    *   Badges on Favorite and Cart icons indicate the number of items.
    *   Optional back navigation button.
    *   Responds to scroll behavior.
*   **Loading and Error States**: The UI handles loading states while fetching data and displays error messages if issues occur.
*   **User Feedback**: Provides user feedback for actions like adding items to favorites or cart via Toast messages.
*   **Edge-to-Edge Display**: Utilizes the full screen for an immersive user experience.
*   **Splash Screen**: Custom splash screen for a polished app launch.

## Technical Details
*   **Language**: Kotlin
*   **UI Toolkit**: Jetpack Compose
*   **Architecture**: MVVM (Model-View-ViewModel)
    *   **ViewModel (`MainViewModel.kt`)**: Manages UI state (`MainScreenUiState.kt`) and handles business logic for the main screen. Exposes data using Kotlin Flows (`StateFlow` and `SharedFlow`).
    *   **UI Layer (`ShopFlowScreen.kt`, `ShopScreenContent.kt`, etc.)**: Composable functions responsible for rendering the UI based on the state provided by the ViewModel.
*   **Dependency Injection**: Koin (`AppModule.kt`, `ShopFlowApplication.kt`) is used for managing dependencies.
*   **Key Jetpack Libraries**:
    *   Compose UI, Material3, Foundation, Animation
    *   Lifecycle (ViewModel, LiveData, Runtime Compose)
    *   Activity Compose
    *   Core KTX
    *   Splashscreen
*   **Code Structure**: The project is organized into feature-based packages for clarity and maintainability (e.g., `view`, `viewmodel`, `model`, `theme`, `utils`).
*   **Styling and Theming**: Custom themes, colors, and typography are defined in the `ui.theme` package. Specific fonts are used for branding.
*   **Scrolling Behavior**: The Top App Bar and content lists are designed with appropriate scroll behaviors for a smooth user experience.
*   **Sample Data**: Includes sample data for promotions, categories, and products for demonstration purposes (`samplePromotions.kt`, `sampleCategories.kt`, `sampleProducts.kt`).

## Code Highlights and Composables
*   **`ShopFlowScreen.kt`**: The main entry point for the UI, orchestrating the display of the top app bar, loading/error states, and the main shop content.
*   **`ShopScreenContent.kt`**: Lays out the core content of the shop, including promotion banners, category lists, and product grids using `LazyColumn` or similar scrollable composables.
*   **`ShopFlowTopAppBar.kt`**: A reusable top app bar component with dynamic title, navigation, actions, and badges. Includes a `BadgedIconButtonBox` helper composable.
*   **`PromotionBanner.kt`**: Implements an auto-cycling horizontal pager for promotional items with page indicators.
*   **`CategorySection.kt`**: Displays a horizontally scrollable list of `CategoryChip` composables, each representing a product category.
*   **`ProductSection.kt` (and associated `ProductCard.kt`)**: Manages the display of products in a grid. `ProductCard.kt` renders individual product details and interaction elements.
*   **Data Classes (`Product.kt`, `ProductCategory.kt`, `PromotionItem.kt`, `MainScreenUiState.kt`)**: Define the structure of the data used throughout the application.

## Build Configuration
*   **`build.gradle.kts (:app)`**: Configures the Android application plugin, Kotlin, Compose, SDK versions, build types, and dependencies.
*   **`libs.versions.toml`**: Centralizes dependency versions using the Gradle version catalog.

## How to Build and Run
1.  Clone the repository.
2.  Open the project in Android Studio.
3.  Ensure you have the Android SDK specified in `compileSdk` (version 35 as per the provided `build.gradle.kts`) installed.
4.  Let Android Studio sync the Gradle files and download dependencies.
5.  Run the application on an Android emulator or a physical device (minSdk 24).

## Creative Choices & Usability Considerations
*   **Clear Visual Hierarchy**: The screen is structured to guide the user's attention from promotions to categories and then to products.
*   **Actionable Elements**: Buttons for favorites, cart, and "See All" are clearly visible and interactive.
*   **Feedback Mechanisms**: Badges on icons and toast messages provide immediate feedback for user actions.
*   **Scrollable Content**: The layout is designed for easy browsing of potentially long lists of items.
*   **Aesthetic Design**: Custom fonts and color schemes are used to create a visually appealing interface.

This project fulfills the assignment requirements by implementing a functional and well-structured Shop Flow screen using Jetpack Compose, emphasizing clarity, usability, and modern Android development practices.
