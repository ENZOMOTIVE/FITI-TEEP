package com.example.fiti_teep.network



import android.content.Context
import android.util.Log
import com.example.fiti_teep.data_layer.chatScreen.UserInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import android.util.Base64


fun sendMessageAI(
    userMessageInput: UserInput,
    context: Context,
    apiKey: String,
    onResult: (String) -> Unit,
    onError: (String) -> Unit
) {
    val client = OkHttpClient()

    val messages = JSONArray()

    val contentList = JSONArray()

    // Add text if available
    userMessageInput.text?.let { text ->
        contentList.put(JSONObject().apply {
            put("type", "text")
            put("text", text)
        })
    }

    // Add image if available
    userMessageInput.imageUri?.let { uri ->
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val imageBytes = inputStream?.readBytes()
            inputStream?.close()

            if (imageBytes != null) {
                val base64Image = Base64.encodeToString(imageBytes, Base64.NO_WRAP)
                contentList.put(JSONObject().apply {
                    put("type", "image_url")
                    put("image_url", JSONObject().apply {
                        put("url", "data:image/jpeg;base64,$base64Image")
                    })
                })
            }
        } catch (e: Exception) {
            onError("Image encoding failed: ${e.message}")
            return
        }
    }

    // Prompt to inform Behave like a Vet
    messages.put(JSONObject().apply {
        put("role", "system")
        put("content", "You are a helpful, friendly virtual veterinarian assistant. Provide guidance about pet health based on symptoms. Be clear that this is not a substitute for professional diagnosis. If symptoms seem serious, always recommend visiting a real vet.")
    })

    // Wrap user message
    messages.put(JSONObject().apply {
        put("role", "user")
        put("content", contentList)
    })

    val jsonBody = JSONObject().apply {
        put("model", "gpt-4o")
        put("messages", messages)
        put("max_tokens", 300)
    }

    val request = Request.Builder()
        .url("https://api.openai.com/v1/chat/completions")
        .post(RequestBody.create("application/json".toMediaTypeOrNull(), jsonBody.toString()))
        .addHeader("Authorization", "Bearer $apiKey")
        .addHeader("Content-Type", "application/json")
        .build()

    CoroutineScope(Dispatchers.IO).launch {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("OpenAI", "Request failed: ${e.message}")
                onError(e.message ?: "Unknown error")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        onError("Unexpected response: ${response.code}")
                        return
                    }

                    val responseBody = response.body?.string()
                    try {
                        val json = JSONObject(responseBody ?: "")
                        val reply = json.getJSONArray("choices")
                            .getJSONObject(0)
                            .getJSONObject("message")
                            .getString("content")
                        onResult(reply.trim())
                    } catch (e: Exception) {
                        onError("Parse error: ${e.message}")
                    }
                }
            }
        })
    }
}
