package com.example.shopflow.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.shopflow.R
import com.example.shopflow.model.dataclass.UserMessage
import com.example.shopflow.ui.theme.PrimaryContainerColor
import com.example.shopflow.ui.theme.PrimaryGreen
import com.example.shopflow.ui.theme.SecondaryTextColorOnPrimaryGreen
import com.example.shopflow.ui.theme.SurfaceColor
import com.example.shopflow.ui.theme.centuryOldStyleStdBoldFamily
import com.example.shopflow.ui.theme.neuzeitSBookFamily
import com.example.shopflow.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Composable function for the main screen of the ShopFlow application.
 *
 * This screen displays a top app bar, and depending on the UI state, it shows:
 * - A loading indicator if data is being fetched.
 * - An error message if there are errors.
 * - The main shop content ([ShopScreenContent]) if data is successfully loaded.
 *
 * It observes UI state changes from the [MainViewModel] and handles one-time events
 * like displaying Toast messages.
 *
 * @param mainViewModel The [MainViewModel] instance, typically obtained via Koin dependency injection.
 *                      It provides the UI state and handles user interactions.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopFlowScreen(
    mainViewModel: MainViewModel = koinViewModel<MainViewModel>() // Get the ViewModel instance
) {
    val uiState by mainViewModel.uiState.collectAsStateWithLifecycle() // Collect the state

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val context = LocalContext.current // Get context for Toast

    // LaunchedEffect to observe userMessages from ViewModel for one-time events like Toasts
    LaunchedEffect(key1 = Unit) { // Use key1 = Unit to run once when the Composable enters composition
        mainViewModel.userMessages.collect { userMessage ->
            when (userMessage) {
                is UserMessage.ToastMessage -> {
                    Toast.makeText(context, userMessage.message, Toast.LENGTH_SHORT).show()
                }
                // Handle other UserMessage types here if we add them
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = SurfaceColor,
        topBar = {
            if (!uiState.isLoading) { // Optionally hide TopAppBar while loading initial data
                ShopFlowTopAppBar(
                    title = stringResource(id = R.string.title_shop),
                    titleFontFamily = centuryOldStyleStdBoldFamily,
                    scrollBehavior = scrollBehavior,
                    modifier = Modifier,
                    showBackButton = true,
                    favouriteCount = uiState.favoriteCount,
                    cartCount = uiState.cartItemCount,
                    badgeContainerColor = PrimaryGreen,
                    badgeContentColor = SecondaryTextColorOnPrimaryGreen,
                    badgeFontFamily = neuzeitSBookFamily,
                    containerColor = SurfaceColor,
                    scrolledContainerColor = PrimaryContainerColor,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    /*
                        `onBackClick`, `onSearchClick`, etc.,
                        would likely trigger navigation actions or calls to a ViewModel
                         */
                    onBackClick = { mainViewModel.onBackClicked() },
                    onSearchClick = { mainViewModel.onSearchClicked() },
                    onFavouriteClick = { mainViewModel.onTopBarFavoriteClicked() },
                    onCartClick = { mainViewModel.onTopBarCartClicked() },
                )
            }
        }) { innerPadding ->
        if (uiState.isLoading) {
            // Show a loading indicator
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else if (uiState.errorMessages.isNotEmpty()) {
            // Show error messages
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: ${uiState.errorMessages.firstOrNull()}") // Simple error display
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                ShopScreenContent(
                    modifier = Modifier,

                    /*
                    In a production app, the samplePromotions, sampleCategories, and
                    sampleProducts would likely come from a ViewModel or a data layer.
                     */
                    promotions = uiState.promotions,
                    categories = uiState.categories,
                    products = uiState.products,

                    /*
                    Similarly, the `onBannerClick`, `onCategoryClick`, etc.,
                    would likely trigger navigation actions or calls to a ViewModel.
                     */
                    onBannerClick = { mainViewModel.onPromotionBannerClicked(it) },
                    onCategoryClick = { mainViewModel.onCategoryClicked(it) },
                    onSeeAllCategoryClick = { mainViewModel.onSeeAllCategoriesClicked() },
                    onProductClick = { mainViewModel.onProductClicked(it) },
                    onSeeAllProductClick = { mainViewModel.onSeeAllProductsClicked() },
                    onAddToFavouriteClick = { mainViewModel.onToggleFavorite(it) },
                    onAddToCartClick = { mainViewModel.onToggleCart(it) },
                    onReviewClick = { mainViewModel.onReviewClicked(it) })
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Shop Flow Screen")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopFlowScreenPreview() {
    ShopFlowScreen()
}