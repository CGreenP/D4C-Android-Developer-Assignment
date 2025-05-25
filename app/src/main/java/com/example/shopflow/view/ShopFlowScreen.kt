package com.example.shopflow.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import com.example.shopflow.R
import com.example.shopflow.ui.theme.PrimaryContainerColor
import com.example.shopflow.ui.theme.PrimaryGreen
import com.example.shopflow.ui.theme.SecondaryTextColorOnPrimaryGreen
import com.example.shopflow.ui.theme.SurfaceColor
import com.example.shopflow.ui.theme.centuryOldStyleStdBoldFamily
import com.example.shopflow.ui.theme.neuzeitSBookFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopFlowScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = SurfaceColor,
        topBar = {
            ShopFlowTopAppBar(
                title = stringResource(id = R.string.title_shop),
                titleFontFamily = centuryOldStyleStdBoldFamily,
                scrollBehavior = scrollBehavior,
                modifier = Modifier,
                showBackButton = true,
                favouriteCount = 0,
                cartCount = 1,
                badgeContainerColor = PrimaryGreen,
                badgeContentColor = SecondaryTextColorOnPrimaryGreen,
                badgeFontFamily = neuzeitSBookFamily,
                containerColor = SurfaceColor,
                scrolledContainerColor = PrimaryContainerColor,
                navigationIconContentColor = Color.White,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White,
                onBackClick = {},
                onSearchClick = {},
                onFavouriteClick = {},
                onCartClick = {},
            )
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

        }
    }
}