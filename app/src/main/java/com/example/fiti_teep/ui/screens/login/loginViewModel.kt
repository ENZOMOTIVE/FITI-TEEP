package com.example.fiti_teep.ui.screens.login


import android.net.Uri
import androidx.lifecycle.ViewModel
import com.web3auth.core.Web3Auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

//Koin injected Web3Auth
class LoginViewModel(
    private val web3Auth: Web3Auth
) : ViewModel() {



    // Mutable state flow for internal use
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    fun login() {
        // Temporary mock login logic
        _isLoggedIn.value = true
    }


    }
