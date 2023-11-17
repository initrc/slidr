package io.github.initrc.slidr.feature.chat

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.initrc.slidr.feature.chat.data.SampleData

@Composable
fun ChatScreen(

) = Surface(modifier = Modifier.fillMaxSize()) {
        Conversation(messages = SampleData.conversationSample)
    }
