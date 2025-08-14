package com.example.fiti_teep.ui.screens.login


import android.annotation.SuppressLint
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.example.fiti_teep.data_layer.JavaConstructor.extraLoginOptions
import com.web3auth.core.types.ExtraLoginOptions
import com.web3auth.core.types.LoginParams
import com.web3auth.core.types.Provider
import com.web3auth.core.types.Web3AuthResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import java.util.concurrent.CompletableFuture

class LoginViewModel : ViewModel() {

    // Mutable state flow for internal use
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    @SuppressLint("StaticFieldLeak")
    private lateinit var emailInput: EditText
    private val rpcUrl = "https://1rpc.io/sepolia"





    private fun signIn() {
        val email = emailInput.text.toString()
        val selectedLoginProvider = Provider.EMAIL_PASSWORDLESS   // Can be GOOGLE, FACEBOOK, TWITCH etc.
        val loginParams = LoginParams(
            selectedLoginProvider,         // provider
            null,                           // String s
            extraLoginOptions, // extraLoginOptions
            null,                           // Uri
            null,                           // String s1
            null,                           // MFALevel
            null,                           // Curve
            null                            // String s2
        )


    }
}