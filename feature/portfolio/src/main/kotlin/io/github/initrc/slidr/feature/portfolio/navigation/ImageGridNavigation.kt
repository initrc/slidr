package io.github.initrc.slidr.feature.portfolio.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.initrc.slidr.feature.portfolio.ImageGridScreen
import io.github.initrc.slidr.feature.portfolio.PortfolioViewModel

private const val IMAGE_INDEX = "imageIndex"
const val imageGridRoute = "imageGrid/{$IMAGE_INDEX}"

fun NavGraphBuilder.imageGridScreen(navController: NavController, parentRoute: String) {
    composable(
        route = imageGridRoute,
        arguments = listOf(
            navArgument(IMAGE_INDEX) { type = NavType.IntType},
        )
    ) { backStackEntry ->
        val imageIndex = checkNotNull(backStackEntry.arguments?.getInt(IMAGE_INDEX))
        val parentEntry = remember(backStackEntry) { navController.getBackStackEntry(parentRoute) }
        val portfolioViewModel: PortfolioViewModel = hiltViewModel(parentEntry)
        ImageGridScreen(portfolioViewModel, imageIndex)
    }
}

fun NavController.navigateToImageGrid(index: Int) {
    navigate("imageGrid/$index") {
        launchSingleTop = true
    }
}
