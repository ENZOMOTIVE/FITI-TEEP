package com.example.fiti_teep


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.fiti_teep.ui.theme.FititeepTheme
import com.example.fiti_teep.ui.screens.login.LoginScreen
import com.example.fiti_teep.ui.screens.login.LoginViewModel
import com.web3auth.core.Web3Auth
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.CompletableFuture


class MainActivity : ComponentActivity() {


    private val web3Auth: Web3Auth by inject()


    // Activity level koin Injection
    val loginViewModel: LoginViewModel by viewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val session: CompletableFuture<Void> = web3Auth.initialize()

        session.whenComplete { _, _ ->
            // Now it's safe to handle the intent
            loginViewModel.redirectUri(intent?.data)
        }

        setContent {
            FititeepTheme {
                val navController = rememberNavController()

                // val loginViewModel: LoginViewModel = koinViewModel()


                //passing the intent to the viewmodel
               // loginViewModel.redirectUri(intent?.data)

                // passing the web3Initialize to the login view model
                loginViewModel.sessionCheck(session)

                val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()



                if (isLoggedIn) {

                   Pawpulse(navController)

                }else {
                    LoginScreen(
                        onLoginScreenSuccess = {

                        }
                    )
                }

            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        loginViewModel.redirectUri(intent?.data)

    }

    override fun onResume() {
        super.onResume()
        if (Web3Auth.getCustomTabsClosed()) {
            Toast.makeText(this, "User closed the browser.", Toast.LENGTH_SHORT).show()

            //web3Auth.setResultUrl(null)
            loginViewModel.redirectUri(null)

            Web3Auth.setCustomTabsClosed(false)
        }
    }
}