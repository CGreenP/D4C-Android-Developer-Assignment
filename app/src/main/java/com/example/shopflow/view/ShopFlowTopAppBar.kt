package com.example.shopflow.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.shopflow.R
import com.example.shopflow.ui.theme.PrimaryContainerColor
import com.example.shopflow.ui.theme.PrimaryGreen
import com.example.shopflow.ui.theme.SecondaryTextColorOnPrimaryGreen
import com.example.shopflow.ui.theme.SurfaceColor
import com.example.shopflow.ui.theme.centuryOldStyleStdBoldFamily
import com.example.shopflow.ui.theme.neuzeitSBookFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopFlowTopAppBar(
    title: String,
    titleFontFamily: FontFamily = centuryOldStyleStdBoldFamily,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = true,
    favouriteCount: Int = 0,
    cartCount: Int = 0,
    badgeContainerColor: Color = PrimaryGreen,
    badgeContentColor: Color = SecondaryTextColorOnPrimaryGreen,
    badgeFontFamily: FontFamily = neuzeitSBookFamily,
    containerColor: Color = SurfaceColor,
    scrolledContainerColor: Color = PrimaryContainerColor,
    navigationIconContentColor: Color = Color.White,
    titleContentColor: Color = Color.White,
    actionIconContentColor: Color = Color.White,
    onBackClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    onFavouriteClick: () -> Unit = {},
    onCartClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier, title = {
        Text(
            text = title, style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = titleFontFamily
            )
        )
    }, scrollBehavior = scrollBehavior, navigationIcon = {
        if (showBackButton) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_button_description)
                )
            }
        }
    }, actions = {
        IconButton(
            onClick = onSearchClick
        ) {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = stringResource(id = R.string.search_button_description)
            )
        }
        BadgedIconButtonBox(
            badgeCount = favouriteCount,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.favourite_button_description)
                )
            },
            badgeContainerColor = badgeContainerColor,
            badgeContentColor = badgeContentColor,
            onClick = onFavouriteClick,
            modifier = Modifier
        )
        BadgedIconButtonBox(
            badgeCount = cartCount,
            icon = {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = stringResource(id = R.string.cart_button_description)
                )
            },
            badgeFontFamily = badgeFontFamily,
            badgeContainerColor = badgeContainerColor,
            badgeContentColor = badgeContentColor,
            onClick = onCartClick,
            modifier = Modifier
        )
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = containerColor,
        scrolledContainerColor = scrolledContainerColor,
        navigationIconContentColor = navigationIconContentColor,
        titleContentColor = titleContentColor,
        actionIconContentColor = actionIconContentColor
    )
    )
}

@Composable
fun BadgedIconButtonBox(
    modifier: Modifier = Modifier,
    badgeCount: Int = 0,
    badgeFontFamily: FontFamily = neuzeitSBookFamily,
    badgeContainerColor: Color = Color.Green,
    badgeContentColor: Color = Color.Black,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        BadgedBox(
            modifier = modifier, badge = {
                if (badgeCount > 0) {
                    Badge(
                        containerColor = badgeContainerColor, contentColor = badgeContentColor
                    ) {
                        Text(
                            text = badgeCount.toString(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontFamily = badgeFontFamily, fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }) {
            icon()
        }
    }
}

@Preview(showBackground = true, name = "Top App Bar with Badges")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopFlowTopAppBarPreview() {
    ShopFlowTopAppBar(
        title = "Shop",
        titleFontFamily = centuryOldStyleStdBoldFamily,
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        modifier = Modifier,
        showBackButton = true,
        favouriteCount = 5,
        cartCount = 3,
        badgeContainerColor = PrimaryGreen,
        badgeContentColor = SecondaryTextColorOnPrimaryGreen,
        badgeFontFamily = neuzeitSBookFamily,
        containerColor = SurfaceColor,
        scrolledContainerColor = PrimaryContainerColor,
        navigationIconContentColor = Color.White,
        titleContentColor = Color.White,
        actionIconContentColor = Color.White
    )
}

@Preview(showBackground = false, name = "Top App Bar No Nav Icon No Badges")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopFlowTopAppBarPreviewNoNavNoBadges() {
    ShopFlowTopAppBar(
        title = "Home",
        titleFontFamily = centuryOldStyleStdBoldFamily,
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        showBackButton = false,
        favouriteCount = 0,
        cartCount = 0,
        modifier = Modifier,
        badgeContainerColor = PrimaryGreen,
        badgeContentColor = SecondaryTextColorOnPrimaryGreen,
        badgeFontFamily = neuzeitSBookFamily,
        containerColor = SurfaceColor,
        scrolledContainerColor = PrimaryContainerColor,
        navigationIconContentColor = Color.White,
        titleContentColor = Color.White,
        actionIconContentColor = Color.White
    )
}