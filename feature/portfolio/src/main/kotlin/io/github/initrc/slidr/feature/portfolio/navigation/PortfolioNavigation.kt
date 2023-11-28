package io.github.initrc.slidr.feature.portfolio.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.initrc.slidr.feature.portfolio.PortfolioScreen

private const val USER_ID = "userId"
const val portfolioRoute = "portfolio/{$USER_ID}"

fun NavGraphBuilder.portfolioScreen(onChatClick: (String) -> Unit) {
    composable(
        route = portfolioRoute,
        arguments = listOf(
            navArgument(USER_ID) {
                type = NavType.StringType
                defaultValue = "kyz"
            }
        )
    ) {
        PortfolioScreen(
            onChatClick = onChatClick
        )
    }
}

internal data class PortfolioArgs(val userId: String)
internal fun SavedStateHandle.toPortfolioArgs() = PortfolioArgs(checkNotNull(this[USER_ID]))
