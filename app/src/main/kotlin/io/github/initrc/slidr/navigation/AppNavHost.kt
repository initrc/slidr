package io.github.initrc.slidr.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.initrc.slidr.feature.chat.navigation.chatScreen
import io.github.initrc.slidr.feature.chat.navigation.navigateToChat
import io.github.initrc.slidr.feature.portfolio.navigation.imageGridScreen
import io.github.initrc.slidr.feature.portfolio.navigation.navigateToImageGrid
import io.github.initrc.slidr.feature.portfolio.navigation.portfolioRoute
import io.github.initrc.slidr.feature.portfolio.navigation.portfolioScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = portfolioRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier,
    ) {
        portfolioScreen(
            onChatClick = navController::navigateToChat,
            onImageClick = navController::navigateToImageGrid,
        )
        chatScreen()
        imageGridScreen(navController, portfolioRoute)
    }
}