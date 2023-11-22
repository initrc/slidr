package io.github.initrc.slidr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.initrc.slidr.feature.chat.ChatScreen
import io.github.initrc.slidr.feature.chat.ChatViewModel
import io.github.initrc.slidr.ui.theme.SlidrTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlidrTheme {
                ChatScreen(chatViewModel)
            }
        }
    }
}
