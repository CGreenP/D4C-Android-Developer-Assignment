package com.example.shopflow.view

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopflow.R
import com.example.shopflow.debug.samplePromotions
import com.example.shopflow.model.dataclass.PromotionItem
import com.example.shopflow.ui.theme.PrimaryContainerColor
import com.example.shopflow.ui.theme.PrimaryGreen
import com.example.shopflow.ui.theme.PrimaryTextColorOnSurfaceColor
import com.example.shopflow.ui.theme.SecondaryTextColorOnPrimaryGreen
import com.example.shopflow.ui.theme.neuzeitSBookFamily
import com.example.shopflow.ui.theme.neuzeitSltStdBookFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A Composable function that displays a banner of promotions.
 *
 * This banner uses a HorizontalPager to display a list of [PromotionItem]s.
 * It supports auto-cycling through the promotions and provides indicators
 * at the bottom to show the current promotion.
 *
 * If the list of promotions is empty, it displays a message indicating that
 * there are no promotions available.
 *
 * @param modifier The modifier to be applied to the banner.
 * @param promotions A list of [PromotionItem]s to be displayed in the banner.
 * @param autoCycleDelayMillis The delay in milliseconds before automatically cycling to the next promotion.
 *                             Set to 0 or a negative value to disable auto-cycling. Defaults to 5000L (5 seconds).
 * @param bannerContainer The drawable resource ID for the background of each promotion card.
 *                        Defaults to `R.drawable.banner_card`.
 * @param bannerHeadlineFontFamily The font family to be used for the headline text in the promotion card.
 *                                 Defaults to `neuzeitSBookFamily`.
 * @param bannerSubtitleFontFamily The font family to be used for the subtitle text in the promotion card.
 *                                 Defaults to `neuzeitSltStdBookFamily`.
 * @param bannerDateRangeFontFamily The font family to be used for the date range text in the promotion card.
 *                                  Defaults to `neuzeitSBookFamily`.
 * @param bannerContainerColor The background color of the promotion card container.
 *                            Defaults to `PrimaryContainerColor`.
 * @param bannerContentColor The color of the text and other content within the promotion card.
 *                          Defaults to `PrimaryTextColorOnSurfaceColor`.
 * @param bannerDateRangeContainerColor The background color of the date range container within the promotion card.
 *                                     Defaults to `PrimaryGreen`.
 * @param bannerDateRangeContentColor The color of the date range text within the promotion card.
 *                                   Defaults to `SurfaceColor`.
 * @param bottomIndicatorPrimaryContainerColor The color of the selected bottom indicator.
 *                                            Defaults to `PrimaryGreen`.
 * @param bottomIndicatorSurfaceVariantColor The color of the unselected bottom indicators.
 *                                          Defaults to `PrimaryContainerColor`.
 */
@Composable
fun PromotionBanner(
    modifier: Modifier = Modifier,
    promotions: List<PromotionItem>,
    autoCycleDelayMillis: Long = 5000L, // Default to 5 seconds
    bannerContainer: Int = R.drawable.banner_card,
    bannerHeadlineFontFamily: FontFamily = neuzeitSBookFamily,
    bannerSubtitleFontFamily: FontFamily = neuzeitSltStdBookFamily,
    bannerDateRangeFontFamily: FontFamily = neuzeitSBookFamily,
    bannerContainerColor: Color = PrimaryContainerColor,
    bannerContentColor: Color = PrimaryTextColorOnSurfaceColor,
    bannerDateRangeContainerColor: Color = PrimaryGreen,
    bannerDateRangeContentColor: Color = SecondaryTextColorOnPrimaryGreen,
    bottomIndicatorPrimaryContainerColor: Color = PrimaryGreen,
    bottomIndicatorSurfaceVariantColor: Color = PrimaryContainerColor,
    onBannerClick: (PromotionItem) -> Unit = {}
) {
    if (promotions.isNotEmpty()) {
        val pagerState = rememberPagerState(pageCount = { promotions.size })

        val coroutineScope = rememberCoroutineScope()

        // Auto-cycle effect
        if (autoCycleDelayMillis > 0) {
            LaunchedEffect(pagerState.currentPage) {
                delay(autoCycleDelayMillis)
                coroutineScope.launch {
                    val nextPage = (pagerState.currentPage + 1) % promotions.size
                    pagerState.animateScrollToPage(
                        page = nextPage, animationSpec = tween(
                            durationMillis = 1000, easing = FastOutSlowInEasing
                        )
                    )
                }
            }
        }

        Box(modifier = modifier) {
            HorizontalPager(
                state = pagerState, modifier = Modifier.fillMaxWidth(), pageSpacing = 16.dp
            ) { pageIndex ->
                PromotionCard(
                    promotion = promotions[pageIndex],
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    bannerContainer = bannerContainer,
                    bannerContainerColor = bannerContainerColor,
                    bannerHeadlineFontFamily = bannerHeadlineFontFamily,
                    bannerSubtitleFontFamily = bannerSubtitleFontFamily,
                    bannerDateRangeFontFamily = bannerDateRangeFontFamily,
                    bannerContentColor = bannerContentColor,
                    bannerDateRangeContainerColor = bannerDateRangeContainerColor,
                    bannerDateRangeContentColor = bannerDateRangeContentColor,
                    onBannerClick = { onBannerClick(promotions[pageIndex]) })
            }

            // Indicators
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.60f)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp),
            ) {
                promotions.indices.forEach { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(width = if (isSelected) 24.dp else 12.dp, height = 12.dp)
                            .background(
                                if (isSelected) bottomIndicatorPrimaryContainerColor else bottomIndicatorSurfaceVariantColor,
                                CircleShape
                            )
                    )
                }
            }
        }
    } else {
        // If the promotions list is empty
        Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(200.dp),
                shape = MaterialTheme.shapes.extraLarge,
            ) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = stringResource(id = R.string.promotion_banner_empty_state),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                        )
                    )
                }
            }
        }
    }
}

/**
 * A composable function that displays a single promotion card.
 *
 * This function is responsible for rendering the visual representation of a promotion,
 * including its title, subtitle, date range, and product image. It also handles
 * click events on the banner.
 *
 * @param promotion The [PromotionItem] data to display in the card.
 * @param modifier Optional [Modifier] to be applied to the card.
 * @param bannerContainer The drawable resource ID for the banner background image.
 * @param bannerHeadlineFontFamily The [FontFamily] to use for the banner headline text.
 * @param bannerSubtitleFontFamily The [FontFamily] to use for the banner subtitle text.
 * @param bannerDateRangeFontFamily The [FontFamily] to use for the banner date range text.
 * @param bannerContainerColor The [Color] to use for tinting the banner background image.
 * @param bannerContentColor The [Color] to use for the text content within the banner.
 * @param bannerDateRangeContainerColor The [Color] to use for the background of the date range element.
 * @param bannerDateRangeContentColor The [Color] to use for the text of the date range element.
 * @param onBannerClick A lambda function to be invoked when the banner is clicked.
 */
@Composable
fun PromotionCard(
    promotion: PromotionItem,
    modifier: Modifier = Modifier,
    bannerContainer: Int = R.drawable.banner_card,
    bannerHeadlineFontFamily: FontFamily = neuzeitSBookFamily,
    bannerSubtitleFontFamily: FontFamily = neuzeitSltStdBookFamily,
    bannerDateRangeFontFamily: FontFamily = neuzeitSBookFamily,
    bannerContainerColor: Color = PrimaryContainerColor,
    bannerContentColor: Color = PrimaryTextColorOnSurfaceColor,
    bannerDateRangeContainerColor: Color = PrimaryGreen,
    bannerDateRangeContentColor: Color = SecondaryTextColorOnPrimaryGreen,
    onBannerClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(containerColor = promotion.backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onBannerClick)
        ) {
            Image(
                painter = painterResource(id = bannerContainer), // Flow Card PNG
                contentDescription = "Promotion Banner Background",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                colorFilter = ColorFilter.tint(bannerContainerColor)
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Left Column for Text and Date
                Column(
                    modifier = Modifier.weight(1f), verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = promotion.title,
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontFamily = bannerHeadlineFontFamily,
                                fontWeight = FontWeight.Bold,
                            ),
                            color = bannerContentColor
                        )
                        Text(
                            text = promotion.subtitle,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = bannerSubtitleFontFamily,
                                fontWeight = FontWeight.Medium
                            ),
                            color = bannerContentColor
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Date
                    Box(
                        modifier = Modifier
                            .background(
                                bannerDateRangeContainerColor, RoundedCornerShape(50)
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = promotion.dateRange,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = bannerDateRangeFontFamily,
                                fontWeight = FontWeight.Bold,
                                color = bannerDateRangeContentColor
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Right: Product Image
                Image(
                    painter = painterResource(id = promotion.productImageResId),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillBounds
                )
            }
        }
    }
}

@Preview(showBackground = false, name = "Promotion Banner")
@Composable
fun PromotionBannerPreview() {
    PromotionBanner(promotions = samplePromotions)
}

@Preview(showBackground = false, name = "Promotion Banner when List is empty")
@Composable
fun PromotionBannerListEmptyPreview() {
    PromotionBanner(promotions = emptyList())
}