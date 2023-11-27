package io.github.initrc.slidr.feature.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.github.initrc.slidr.feature.chat.ChatScreen
import java.net.URLEncoder

private const val USER_ID = "userId"

fun NavGraphBuilder.chatScreen() {
    composable(
        route = "chat/{$USER_ID}",
        arguments = listOf(
            navArgument(USER_ID) { type = NavType.StringType }
        )
    ) {
        ChatScreen()
    }
}

fun NavController.navigateToChat(userId: String) {
    navigate("chat/${userId.urlEncode()}") {
        launchSingleTop = true
    }
}

private fun String.urlEncode() = URLEncoder.encode(this, Charsets.UTF_8.name())
