package io.github.initrc.slidr.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.initrc.slidr.feature.chat.navigation.chatScreen
import io.github.initrc.slidr.feature.chat.navigation.navigateToChat
import io.github.initrc.slidr.feature.portfolio.navigation.portfolioScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "portfolio/{kyz}",
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier,
    ) {
        portfolioScreen(onChatClick = navController::navigateToChat)
        chatScreen()
    }
}