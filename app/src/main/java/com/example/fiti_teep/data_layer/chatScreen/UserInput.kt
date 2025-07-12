package com.example.fiti_teep.data_layer.chatScreen

import android.net.Uri

// Holding class for the input Storage
data class UserInput (
    val text: String ? = null,
    val imageUri: Uri? =null
)