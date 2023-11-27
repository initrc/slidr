package io.github.initrc.slidr.feature.portfolio.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.initrc.slidr.feature.portfolio.PortfolioScreen

private const val USER_ID = "userId"

fun NavGraphBuilder.portfolioScreen(onChatClick: (String) -> Unit) {
    composable(
        route = "portfolio/{$USER_ID}",
        arguments = listOf(
            navArgument(USER_ID) { type = NavType.StringType }
        )
    ) {
        PortfolioScreen(onChatClick = onChatClick)
    }
}
