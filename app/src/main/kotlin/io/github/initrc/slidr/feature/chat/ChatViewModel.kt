package io.github.initrc.slidr.feature.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.initrc.slidr.feature.chat.data.ChatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
): ViewModel() {

    private val welcomeMessage = Message("Hi, ask me about universities.", false)
    private val _messages = MutableStateFlow(listOf(welcomeMessage))
    val messages: StateFlow<List<Message>> = _messages

    fun onSendClick(text: String) {
        appendMessage(listOf(Message(text, true)))
        viewModelScope.launch {
            val messagesFromBot = sendText(text)
            appendMessage(messagesFromBot)
        }
    }

    private fun appendMessage(messages: List<Message>) {
        _messages.value = _messages.value.plus(messages)
    }

    private suspend fun sendText(text: String): List<Message> {
        return chatRepository.searchUniversities(text).map { university ->
            val websites = university.webPages.joinToString("\n")
            Message(
                "${university.name}\nCountry: ${university.country}\n$websites",
                false
            )
        }
    }
}
