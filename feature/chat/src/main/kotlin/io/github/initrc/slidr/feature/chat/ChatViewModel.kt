package io.github.initrc.slidr.feature.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.initrc.slidr.core.data.ChatRepository
import io.github.initrc.slidr.core.model.Message
import io.github.initrc.slidr.feature.chat.navigation.toChatArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val chatRepository: ChatRepository,
) : ViewModel() {

    private val userId = savedStateHandle.toChatArgs().userId
    private val welcomeMessage = Message("Hi, I'm ${userId}. Ask me about universities.", false)
    private val _messages = MutableStateFlow(listOf(welcomeMessage))
    val messages: StateFlow<List<Message>> = _messages

    fun onSendClick(inputText: String) {
        val text = inputText.trim()
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
