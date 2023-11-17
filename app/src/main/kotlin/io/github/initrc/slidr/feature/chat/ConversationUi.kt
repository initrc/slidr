package io.github.initrc.slidr.feature.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.initrc.slidr.feature.chat.data.SampleData
import io.github.initrc.slidr.ui.theme.SlidrTheme

@Composable
fun Conversation(messages: State<List<Message>>, modifier: Modifier) {
    LazyColumn(
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
    ) {
        items(messages.value) { message ->
            MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    SlidrTheme {
        Conversation(
            remember { mutableStateOf(SampleData.chatbotConversation) },
            Modifier)
    }
}
