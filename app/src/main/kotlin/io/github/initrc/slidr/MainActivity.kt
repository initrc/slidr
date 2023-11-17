package io.github.initrc.slidr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.github.initrc.slidr.feature.chat.ChatScreen
import io.github.initrc.slidr.ui.theme.SlidrTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlidrTheme {
                ChatScreen()
            }
        }
    }
}
