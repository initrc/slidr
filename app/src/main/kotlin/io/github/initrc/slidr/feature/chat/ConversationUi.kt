package io.github.initrc.slidr.feature.chat

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.initrc.slidr.feature.chat.data.SampleData
import io.github.initrc.slidr.ui.theme.SlidrTheme

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    SlidrTheme {
        Conversation(SampleData.conversationSample)
    }
}
