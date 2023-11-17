package io.github.initrc.slidr.feature.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {

    private val welcomeMessage = Message("Hi, ask me about schools.", false)
    private val _messages = MutableLiveData(listOf(welcomeMessage))
    val messages: LiveData<List<Message>>
        get() = _messages

    fun onSendClick(text: String) {
        Log.e("david", "onSendClicked: $text")
        appendMessage(Message(text, true))
        viewModelScope.launch {
            val textFromBot = sendText(text)
            appendMessage(Message(textFromBot, false))
        }
    }

    private fun appendMessage(message: Message) {
        val newMessages = _messages.value?.toMutableList() ?: mutableListOf()
        newMessages.add(message)
        _messages.value = newMessages
    }

    private suspend fun sendText(text: String): String {
        delay(1000)
        return "$text $text"
    }
}