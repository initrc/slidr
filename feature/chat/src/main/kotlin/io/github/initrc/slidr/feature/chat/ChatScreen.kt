package io.github.initrc.slidr.feature.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.initrc.slidr.core.model.Message

@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel = hiltViewModel(),
) {
    val messages by chatViewModel.messages.collectAsStateWithLifecycle()
    ChatScreen(messages, chatViewModel::onSendClick)
}

@Composable
fun ChatScreen(
    messages: List<Message>,
    onSendClick: (String) -> Unit,
) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            Conversation(
                messages = messages,
                modifier = Modifier.weight(1f)
            )

            SendText(
                onSendClick = onSendClick,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
