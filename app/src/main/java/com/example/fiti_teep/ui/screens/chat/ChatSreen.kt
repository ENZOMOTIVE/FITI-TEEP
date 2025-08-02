package com.example.fiti_teep.ui.screens.chat

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.fiti_teep.BuildConfig
import com.example.fiti_teep.data_layer.chatScreen.ChatMessage

import com.example.fiti_teep.network.sendMessageAI




@Composable
fun ChatScreen(paddingValues: PaddingValues, viewModel: ChatViewModel) {

//Handles temporary  input single Unit

    // Without using ViewModel
    //var currentInput by remember { mutableStateOf(UserInput()) }

    //Using viewModel
    val currentInput = viewModel.currentInput

    val context = LocalContext.current

    var showHealthCard by remember { mutableStateOf(false) }


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
            // currentInput = currentInput.copy(imageUri = uri)
            viewModel.onImageSelected(uri)

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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = if (message is ChatMessage.UserMessage) {
                            Arrangement.End
                        } else {
                            Arrangement.Start
                        }
                    ) {
                        when (message) {
                            is ChatMessage.UserMessage -> {
                                Column(
                                    horizontalAlignment = Alignment.End,
                                    modifier = Modifier.widthIn(max = 300.dp)
                                ) {
                                    message.text?.let {
                                        Column(
                                            modifier = Modifier
                                                .background(
                                                    color = Color(0xFFFAE6C8) ,
                                                    shape = RoundedCornerShape(12.dp)
                                                )
                                                .padding(12.dp)
                                        ) {
                                            Text(
                                                text = it,
                                                color = Color.Black,
                                                fontSize = 16.sp,
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    }

                                    message.imageUri?.let {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Image(
                                            painter = rememberAsyncImagePainter(it),
                                            contentDescription = "Attached Image",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .align(Alignment.CenterHorizontally)
                                                .height(180.dp)
                                        )
                                    }
                                }
                            }

                            is ChatMessage.AIMessage -> {

                                /*
                                if (message.showHealthCard) {
                                    HealthReportCard()
                                }
                                */


                                Column(
                                    modifier = Modifier
                                        .background(
                                            color = Color(0xFFE8F5E9),
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .padding(12.dp)
                                        .widthIn(max = 300.dp)
                                ) {
                                    Text(
                                        text = message.text,
                                        color = Color(0xFF2E7D32),
                                        fontSize = 16.sp,
                                        style = MaterialTheme.typography.bodyMedium
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
                        onValueChange = { viewModel.onTextChanged(it) },
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
                                viewModel.clearInput()

                                // Actual AI Response

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



                                // Mock Response UI
                                /*
                                Handler(Looper.getMainLooper()).postDelayed({
                                    chatMessages.add(ChatMessage.AIMessage(showHealthCard = true))
                                }, 500)
                            */



                            }
                        }
                    )
                    {
                        Icon(Icons.Default.Send, contentDescription = "Send")
                    }

                }
            }
        }

