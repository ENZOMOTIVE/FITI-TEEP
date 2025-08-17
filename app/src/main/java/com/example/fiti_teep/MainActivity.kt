package com.example.fiti_teep

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.fiti_teep.ui.screens.login.LoginScreen
import com.example.fiti_teep.ui.screens.login.LoginViewModel
import com.example.fiti_teep.ui.theme.FititeepTheme
import com.web3auth.core.Web3Auth
import com.web3auth.core.types.BuildEnv
import com.web3auth.core.types.ExtraLoginOptions
import com.web3auth.core.types.LoginParams

import com.web3auth.core.types.Web3AuthOptions
import com.web3auth.core.types.Web3AuthResponse
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import java.util.concurrent.CompletableFuture
import androidx.core.net.toUri
import com.web3auth.core.types.AuthConnectionConfig
import com.web3auth.core.types.Language
import com.web3auth.core.types.ThemeModes

import com.web3auth.core.types.WalletServicesConfig
import com.web3auth.core.types.WhiteLabelData
import org.torusresearch.fetchnodedetails.types.Web3AuthNetwork
import com.web3auth.core.types.AuthConnection

class MainActivity : ComponentActivity() {

    private lateinit var web3Auth: Web3Auth
    private lateinit var web3: Web3j
    private lateinit var credentials: Credentials
    private lateinit var emailInput: EditText
    private val rpcUrl = "https://1rpc.io/sepolia"





    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val authConfig = ArrayList<AuthConnectionConfig>()


        web3Auth = Web3Auth(
            Web3AuthOptions(
                clientId = "BIYP2ZZSe0kfk9GGUT_ruqFI6wj28jQ0LdclMaoYUd7XeVbg39fJ9ue5ICpa7qnB3EHkyL9fBCuP9Y4-hgB0Zuk",
                redirectUrl = "fitiapp://auth",

                web3AuthNetwork = Web3AuthNetwork.SAPPHIRE_DEVNET,

                authBuildEnv = BuildEnv.TESTING,
                authConnectionConfig = listOf(
                    AuthConnectionConfig(
                        authConnectionId = "enzomotive",
                        authConnection = AuthConnection.EMAIL_PASSWORDLESS,
                        clientId = "BIYP2ZZSe0kfk9GGUT_ruqFI6wj28jQ0LdclMaoYUd7XeVbg39fJ9ue5ICpa7qnB3EHkyL9fBCuP9Y4-hgB0Zuk"
                    )
                )

            ),
            this
        )



        web3Auth.setResultUrl(intent?.data)
        // Call initialize() in onCreate() to check for any existing session.
        val sessionResponse: CompletableFuture<Void> = web3Auth.initialize()
        sessionResponse.whenComplete { _, error ->
            if (error == null) {

                println("PrivKey: " + web3Auth.getPrivateKey())
                println("ed25519PrivKey: " + web3Auth.getEd25519PrivateKey())
                println("Web3Auth UserInfo" + web3Auth.getUserInfo())
                credentials = Credentials.create(web3Auth.getPrivateKey())
                web3 = Web3j.build(HttpService(rpcUrl))
            } else {
                Log.d("MainActivity_Web3Auth", error.message ?: "Something went wrong")
                // Ideally, you should initiate the login function here.
            }
        }


        setContent {
            FititeepTheme {


                var email by rememberSaveable { mutableStateOf("") }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            placeholder = { Text("you@example.com") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Button(
                            onClick = {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Logging in with: $email",
                                    Toast.LENGTH_SHORT
                                ).show()

                                signIn(email)
                            },
                            enabled = email.isNotBlank(),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Login")
                        }

                    }
                }



            }
        }

    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        // Handle user signing in when app is active
        web3Auth.setResultUrl(intent?.data)
    }

    override fun onResume() {
        super.onResume()
        if (Web3Auth.getCustomTabsClosed()) {
            Toast.makeText(this, "User closed the browser.", Toast.LENGTH_SHORT).show()
            web3Auth.setResultUrl(null)
            Web3Auth.setCustomTabsClosed(false)
        }
    }

    private fun signIn( email: String) {

        // IMP START - Login
        val selectedLoginProvider = AuthConnection.EMAIL_PASSWORDLESS  // Can be GOOGLE, FACEBOOK, TWITCH etc.
        val loginParams = LoginParams(selectedLoginProvider, extraLoginOptions = ExtraLoginOptions(login_hint = email))
        val loginCompletableFuture: CompletableFuture<Web3AuthResponse> =
            web3Auth.connectTo(loginParams)
        // IMP END - Login

        loginCompletableFuture.whenComplete { _, error ->
            if (error == null) {
                // Set the sessionId from Web3Auth in App State
                // This will be used when making blockchain calls with Web3j
                credentials = Credentials.create(web3Auth.getPrivateKey())
                web3 = Web3j.build(HttpService(rpcUrl))

            } else {
                Log.d("MainActivity_Web3Auth", error.message ?: "Something went wrong")
            }
        }
    }



}