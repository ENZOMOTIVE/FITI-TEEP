package com.example.fiti_teep.data_layer.chatScreen

import android.net.Uri

// To handle
sealed class ChatMessage{

    // This handles both Image and string in a Single storage unit
    data class UserMessage(val text: String?, val imageUri: Uri?) : ChatMessage()

    // Output Message returned from Ai
    data class AIMessage(val text: String): ChatMessage()

    //Handle Text and Image Separately
    //data class Text(val message: String) : ChatMessage()
    //data class Image(val uri: android.net.Uri) : ChatMessage()


    //MockMessage
    //data class AIMessage(val text: String = "", val showHealthCard: Boolean = false) : ChatMessage()

}
