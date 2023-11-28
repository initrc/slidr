package io.github.initrc.slidr.feature.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.initrc.slidr.core.data.SampleData
import io.github.initrc.slidr.core.design.SlidrTheme
import io.github.initrc.slidr.core.model.Message
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Conversation(messages: List<Message>, modifier: Modifier) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
    ) {
        items(
            items = messages,
            key = { it.id },
        ) { message ->
            MessageCard(message, Modifier.animateItemPlacement())
        }
        coroutineScope.launch {
            listState.animateScrollToItem(index = messages.size - 1)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    SlidrTheme {
        Surface {
            Conversation(
                SampleData.chatbotConversation,
                Modifier)
        }
    }
}
