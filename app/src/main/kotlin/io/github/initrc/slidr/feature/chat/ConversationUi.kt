package io.github.initrc.slidr.feature.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.initrc.slidr.core.data.SampleData
import io.github.initrc.slidr.core.model.Message
import io.github.initrc.slidr.ui.theme.SlidrTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Conversation(messages: State<List<Message>>, modifier: Modifier) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
    ) {
        items(
            items = messages.value,
            key = { it.id },
        ) { message ->
            MessageCard(message, Modifier.animateItemPlacement())
        }
        coroutineScope.launch {
            listState.animateScrollToItem(index = messages.value.size - 1)
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
