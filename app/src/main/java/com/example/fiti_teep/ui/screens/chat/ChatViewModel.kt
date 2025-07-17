package com.example.fiti_teep.ui.screens.chat

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.fiti_teep.data_layer.chatScreen.ChatMessage
import com.example.fiti_teep.data_layer.chatScreen.UserInput

// This class inherits the ViewModel
class ChatViewModel: ViewModel() {

    // Current Input
    var currentInput by mutableStateOf(UserInput())
        private set

    fun onImageSelected(uri: Uri){
        currentInput = currentInput.copy(imageUri = uri)
    }

    fun onTextChanged(newText: String){
        currentInput = currentInput.copy(text = newText)
    }

    fun clearInput(){
        currentInput = UserInput()
    }

    // CHat history
    private val _chatMessages = mutableStateListOf<ChatMessage>(
            ChatMessage.AIMessage("Hi, how can I help you today?")
        )

    val chatMessages: List<ChatMessage> get() = _chatMessages


    fun addUserMessage(message: ChatMessage.UserMessage) {
        _chatMessages.add(message)
    }

    fun addAIMessage(message: ChatMessage.AIMessage) {
        _chatMessages.add(message)
    }

    fun addErrorMessage(error: String) {
        _chatMessages.add(ChatMessage.AIMessage("Error: $error"))
    }



}