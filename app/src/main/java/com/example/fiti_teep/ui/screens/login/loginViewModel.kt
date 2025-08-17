package com.example.fiti_teep.ui.screens.login


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    // Use Compose state to make it reactive
    var isLoggedIn = mutableStateOf(false)
        private set

    fun login(username: String, password: String) {
        isLoggedIn.value = username == "admin" && password == "1234"
    }
}
