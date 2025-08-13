package com.example.fiti_teep.ui.screens.chat

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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

    val chatMessages = mutableStateListOf<ChatMessage>(
        ChatMessage.AIMessage("Hi, how can I help you today?")
    )

    fun addMessage(message: ChatMessage) {
        chatMessages.add(message)
    }

}