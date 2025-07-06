package com.example.fiti_teep.network



import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaTypeOrNull


fun sendMessageAI(
    userMessageInput: String,
    apiKey: String,
    onResult: (String) -> Unit,
    onError: (String) -> Unit
) {
    val client = OkHttpClient()

    val jsonBody = JSONObject().apply {
        put("model", "gpt-3.5-turbo")
        put("messages", JSONArray().apply {
            put(JSONObject().apply {
                put("role", "user")
                put("content", userMessageInput)
            })
        })
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
