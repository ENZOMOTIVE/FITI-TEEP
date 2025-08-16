package com.example.fiti_teep.ui.screens.login


import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.web3auth.core.Web3Auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import java.util.concurrent.CompletableFuture

//Koin injected Web3Auth
class LoginViewModel (
    private val web3Auth: Web3Auth
) : ViewModel() {

    private lateinit var web3: Web3j
    private lateinit var credentials: Credentials

    // Mutable state flow for internal use
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    fun redirectUri(uri: Uri?) {
        if (uri == null) {
            println("The intent data is null")

        } else {
            println("The intent data is not null: $uri")
            web3Auth.setResultUrl(uri)
        }
    }

    // Condition to check if the session exists
    fun sessionCheck(session: CompletableFuture<Void>) {
        session.whenComplete { _, error ->
            if (error == null) {
                credentials = Credentials.create(web3Auth.privkey)
                web3 = Web3j.build(HttpService("https://1rpc.io/sepolia"))
            } else {
                Log.d("Login_ViewModel Web3Auth", error.message ?: "Something went wrong")
                // Ideally, you should initiate the login function here.
            }
        }
    }









    fun login() {
        // Temporary mock login logic
        _isLoggedIn.value = true
    }

    }
