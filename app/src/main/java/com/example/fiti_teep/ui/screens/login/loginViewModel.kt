package com.example.fiti_teep.ui.screens.login


import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.web3auth.core.Web3Auth
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
            web3Auth.setResultUrl(null)


        } else {
            println("The intent data is not null: $uri")
            web3Auth.setResultUrl(uri)
        }
    }

    // Condition to check if the session exists
    fun sessionCheck(session: CompletableFuture<Void>) {
        session.whenComplete { _, error ->
            if (error == null) {

                try {
                    val privKey = web3Auth.privkey
                    if (privKey != null && privKey.isNotEmpty()) {
                        credentials = Credentials.create(privKey)
                        web3 = Web3j.build(HttpService("https://1rpc.io/sepolia"))
                        _isLoggedIn.value = true
                        println("Session restored successfully")
                    }
                } catch (e: Exception) {
                    Log.e("LoginViewModel", "Error restoring session: ${e.message}")
                }

            } else {
                Log.d("Login_ViewModel Web3Auth", error.message ?: "Something went wrong")
                // Ideally, you should initiate the login function here.
                _isLoggedIn.value = false

            }
        }
    }









    fun signIn(email: String) {
        if (email.isBlank()) return

        val emailInput = email
        val selectedLoginProvider = Provider.EMAIL_PASSWORDLESS   // Can be GOOGLE, FACEBOOK, TWITCH etc.
        val loginParams = LoginParams(
            selectedLoginProvider,
            null,
            ExtraLoginOptions(
                null, // additionalParams
                null, // domain
                null, // client_id
                null, // leeway
                null, // verifierIdField
                null, // isVerifierIdCaseSensitive
                null, // display
                null, // prompt
                null, // max_age
                null, // ui_locales
                null, // id_token
                null, // id_token_hint
                email, // login_hint
                null, // acr_values
                null, // scope
                null, // audience
                null, // connection
                null, // state
                null, // response_type
                null, // nonce
                null  // redirect_uri
            ),
            null,
            null,
            null,
            null,
            null

        )

        val loginCompletableFuture: CompletableFuture<Web3AuthResponse> =
            web3Auth.login(loginParams)

        loginCompletableFuture.whenComplete { response, error ->


            if (error == null && response != null) {
                try {
                    // Login successful
                    val privKey = web3Auth.privkey
                    if (privKey != null && privKey.isNotEmpty()) {
                        credentials = Credentials.create(privKey)
                        web3 = Web3j.build(HttpService("https://1rpc.io/sepolia"))
                        _isLoggedIn.value = true

                        println("Login successful!")
                        println("PrivKey: $privKey")
                        println("UserInfo: ${web3Auth.userInfo}")
                    } else {
                        Log.e("LoginViewModel", "Private key is null or empty after successful login")
                    }
                } catch (e: Exception) {
                    Log.e("LoginViewModel", "Error processing login response: ${e.message}")
                }
            } else {
                Log.d("LoginViewModel", error?.message ?: "Login failed")
            }
        }
    }


    }




