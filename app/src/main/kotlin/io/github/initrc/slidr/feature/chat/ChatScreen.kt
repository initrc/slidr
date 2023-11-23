package io.github.initrc.slidr.feature.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel,
) {
    val messages = chatViewModel.messages.collectAsStateWithLifecycle()
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            Conversation(
                messages = messages,
                modifier = Modifier
                    .weight(1f)
            )

            SendText(
                chatViewModel::onSendClick,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
