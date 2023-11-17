package io.github.initrc.slidr.feature.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.initrc.slidr.feature.chat.data.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {

    private val welcomeMessage = Message("Hi, ask me about universities.", false)
    private val _messages = MutableLiveData(listOf(welcomeMessage))
    val messages: LiveData<List<Message>>
        get() = _messages

    private val chatRepository = ChatRepository()

    fun onSendClick(text: String) {
        Log.e("david", "onSendClicked: $text")
        appendMessage(listOf(Message(text, true)))
        viewModelScope.launch {
            val messagesFromBot = sendText(text)
            appendMessage(messagesFromBot)
        }
    }

    private fun appendMessage(messages: List<Message>) {
        val newMessages = _messages.value?.toMutableList() ?: mutableListOf()
        newMessages.addAll(messages)
        _messages.value = newMessages
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