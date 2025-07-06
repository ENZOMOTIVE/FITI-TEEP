package com.example.fiti_teep.ui.screens.chat

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.fiti_teep.BuildConfig
import com.example.fiti_teep.network.sendMessageAI


// To handle both Image and Texts
sealed class ChatMessage{

    // This handles both Image and string in a Single storage unit
    data class UserMessage(val text: String?, val imageUri: Uri ?) : ChatMessage()

    // Output Message returned from Ai
    data class AIMessage(val text: String): ChatMessage()

    //Handle Text and Image Separately
    //data class Text(val message: String) : ChatMessage()
    //data class Image(val uri: android.net.Uri) : ChatMessage()
}

// Holding class for the input Storage
data class UserInput (
    val text: String ? = null,
    val imageUri: Uri? =null
)



@Composable
fun ChatScreen(paddingValues: PaddingValues) {

//Handles temporary  input single Unit
    var currentInput by remember { mutableStateOf(UserInput()) }

    val context = LocalContext.current

    //Handles Temporary input storage separate
    // keeps Text the input of the user
    //var messageText by remember { mutableStateOf("") }
    // Image input
    //var selectedImageUri by remember { mutableStateOf<android.net.Uri?>(null) }


    // Container to store the messages
    val chatMessages = remember {
        mutableStateListOf<ChatMessage>(
            ChatMessage.AIMessage("Hi, how can I help you today?")
        )
    }


    // Photo Picker
    val pickMedia = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            //selectedImageUri = uri
            currentInput = currentInput.copy(imageUri = uri)
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
                        is ChatMessage.AIMessage -> {
                            Text(
                                text = "AI: ${message.text}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                color = Color.Gray
                            )
                        }

                        is ChatMessage.UserMessage -> {
                            Column(modifier = Modifier.padding(8.dp)) {
                                message.text?.let {
                                    Text(
                                        text = "You: $it",
                                        modifier = Modifier.fillMaxWidth(),
                                        color = Color.Black
                                    )
                                }
                                message.imageUri?.let {
                                    Image(
                                        painter = rememberAsyncImagePainter(it),
                                        contentDescription = "Attached Image",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 4.dp)
                                            .height(200.dp)
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }

        // Preview of the selected Image
        currentInput.imageUri?.let{ uri ->
            Image(
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
                        value = currentInput.text ?: "",
                        onValueChange = { currentInput = currentInput.copy(text = it) },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Type a message...") },
                        maxLines = 3,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFB2F5D3),
                            unfocusedContainerColor = Color.LightGray,
                            cursorColor = Color(0xFF00BF63),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )



                    // Send User Input
                    IconButton(
                        onClick = {
                            if (!currentInput.text.isNullOrBlank() || currentInput.imageUri != null) {
                                chatMessages.add(
                                    ChatMessage.UserMessage(
                                        text = currentInput.text,
                                        imageUri = currentInput.imageUri
                                    )
                                )

                                // Store the text only message to send to LLM
                                //val userText = currentInput.text ?: ""
                                val userText = currentInput
                                
                                //Clear the Input
                                currentInput = UserInput()


                                sendMessageAI(
                                    userMessageInput = userText,
                                    apiKey = BuildConfig.OPENAI_API_KEY,
                                    //AI message directly added to chat container
                                    onResult = { aiReply ->
                                        chatMessages.add(ChatMessage.AIMessage(aiReply))
                                    },
                                    onError = { error ->
                                        chatMessages.add(ChatMessage.AIMessage("Error: $error"))
                                    },
                                    context = context
                                )
                            }
                        }
                    )
                    {
                        Icon(Icons.Default.Send, contentDescription = "Send")
                    }

                }
            }
        }

