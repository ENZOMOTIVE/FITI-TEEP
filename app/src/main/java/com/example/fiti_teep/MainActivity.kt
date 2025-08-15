package com.example.fiti_teep


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.privacysandbox.ads.adservices.measurement.DeletionRequest
import com.example.fiti_teep.ui.theme.FititeepTheme
import com.example.fiti_teep.ui.screens.login.LoginScreen
import com.example.fiti_teep.ui.screens.login.LoginViewModel
import com.web3auth.core.Web3Auth
import com.web3auth.core.types.BuildEnv
import com.web3auth.core.types.Network
import com.web3auth.core.types.Web3AuthOptions
import androidx.core.net.toUri
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import java.util.concurrent.CompletableFuture
import android.util.Log
import io.ktor.client.HttpClient
import org.koin.android.ext.android.inject
import org.koin.compose.koinInject
import org.koin.core.context.GlobalContext


class MainActivity : ComponentActivity() {
    

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()











        setContent {
            FititeepTheme {
                val navController = rememberNavController()

                val loginViewModel: LoginViewModel = viewModel()

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
}