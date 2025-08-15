package com.example.fiti_teep.ui.screens.chat

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fiti_teep.BuildConfig
import com.example.fiti_teep.data_layer.chatScreen.ChatMessage
import com.example.fiti_teep.data_layer.chatScreen.UserInput
import kotlinx.coroutines.launch

// This class inherits the ViewModel
class ChatViewModel(private val repository: ChatRepository): ViewModel() {

    //API Key
    val openai_apiKey = BuildConfig.OPENAI_API_KEY



    // Current Input
    var currentInput by mutableStateOf(UserInput())
        private set

    val chatMessages = mutableStateListOf<ChatMessage>(
        ChatMessage.AIMessage("Hi, how can I help you today?")
    )

    fun onTextChanged(newText: String){
        currentInput = currentInput.copy(text = newText)
    }

    fun onImageSelected(uri: Uri){
        currentInput = currentInput.copy(imageUri = uri)
    }

    fun clearInput(){
        currentInput = UserInput()
    }


    fun addMessage(message: ChatMessage) {
        chatMessages.add(message)
    }

    fun sendMessage(context: Context) {
        val input = currentInput
        if(input.text.isNullOrBlank() && input.imageUri == null  ) return  // exit the function

        addMessage(ChatMessage.UserMessage(input.text,input.imageUri))
        clearInput()

        viewModelScope.launch {
        val result = repository.sendMessageAI(
            input,
            context,
            openai_apiKey,
            onResult = { aiReply ->
                addMessage(ChatMessage.AIMessage(aiReply))
            },
            onError = {
                    error ->
                addMessage(ChatMessage.AIMessage("Error: $error"))
            }
        )
        }
    }

}