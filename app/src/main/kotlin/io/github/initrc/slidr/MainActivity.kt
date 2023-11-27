package io.github.initrc.slidr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.initrc.slidr.core.design.SlidrTheme
import io.github.initrc.slidr.feature.chat.ChatViewModel
import io.github.initrc.slidr.feature.portfolio.PortfolioScreen
import io.github.initrc.slidr.feature.portfolio.PortfolioViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val chatViewModel: ChatViewModel by viewModels()
    private val portfolioViewModel: PortfolioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlidrTheme {
                // ChatScreen(chatViewModel)
                PortfolioScreen(portfolioViewModel)
            }
        }
    }
}
