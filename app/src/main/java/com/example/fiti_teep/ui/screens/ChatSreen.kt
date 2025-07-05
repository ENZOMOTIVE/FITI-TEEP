package com.example.fiti_teep.ui.screens

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

// To handle both Image and Texts
sealed class ChatMessage{
    data class Text(val message: String) : ChatMessage()
    data class Image(val uri: android.net.Uri) : ChatMessage()
}

@Composable
fun ChatScreen(paddingValues: PaddingValues) {


    // keeps Text the input of the user
    var messageText by remember { mutableStateOf("") }

    // Image input
    var selectedImageUri by remember { mutableStateOf<android.net.Uri?>(null) }


    // Container to store the messages
    val chatMessages = remember {
        mutableStateListOf<ChatMessage>(
            ChatMessage.Text("Hi, how can I help you today?")
        )
    }

    // Photo Picker
    val pickMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            selectedImageUri = uri
            Log.d("PhotoPicker", "Selected URI: $uri")
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 8.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(chatMessages) { message ->
                    when (message) {
                        is ChatMessage.Text -> {
                            Text(
                                text = message.message,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                color = Color.Black
                            )
                        }

                        is ChatMessage.Image -> {
                            androidx.compose.foundation.Image(
                                painter = rememberAsyncImagePainter(message.uri),
                                contentDescription = "Attached Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .height(200.dp)
                            )
                        }
                    }
                }
            }
        }

        selectedImageUri?.let { uri ->
            androidx.compose.foundation.Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = "Selected Image Preview",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(150.dp)
            )
        }


        // Input Container
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = {
                        // Launch image picker
                        pickMedia.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )

                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Attach")
                    }

                    TextField(
                        value = messageText,
                        onValueChange = { messageText = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Type a message...") },
                        maxLines = 3
                    )

                    IconButton(
                        onClick = {
                            if (selectedImageUri != null) {
                                chatMessages.add(ChatMessage.Image(selectedImageUri!!))
                                selectedImageUri = null // clear preview after sending
                            }

                            if (messageText.isNotBlank()) {
                                chatMessages.add(ChatMessage.Text("You: $messageText"))
                                messageText = ""
                            }
                        }
                    ) {
                        Icon(Icons.Default.Send, contentDescription = "Send")
                    }

                }
            }
        }

