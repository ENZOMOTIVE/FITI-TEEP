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
import org.koin.core.context.GlobalContext


class MainActivity : ComponentActivity() {


    private lateinit var web3Auth: Web3Auth
    private lateinit var web3: Web3j
    private val rpcUrl = "https://1rpc.io/sepolia"

    private lateinit var credentials: Credentials





    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        web3Auth = Web3Auth(
        Web3AuthOptions(
            // Client id of Web3Auth client id
            "BIYP2ZZSe0kfk9GGUT_ruqFI6wj28jQ0LdclMaoYUd7XeVbg39fJ9ue5ICpa7qnB3EHkyL9fBCuP9Y4-hgB0Zuk",
            // Blockchain Network
            Network.SAPPHIRE_DEVNET,
            // Production Enviorenment
            BuildEnv.PRODUCTION,
            // Redirect Url : The Login Redirect Url
            "com.example.fiti_teep://auth".toUri(),
            // Web3Auth SDK URL
            "",
            // White Label: Optional UI Customization
            null,
            // Login Config: Optional login method configuration
            null,
            // useCoreKitKey: Optional Boolean, advanced use
            null,
            // Chain Name Space: Optional chain selection, e.g., EVM or Solana
            null,
            //  Multi factor Authorization: Optional Configuration
            null,
            // Session time: optional
            null,
            // Wallet Sdk Url: optional
            null,
            //dashboard url: optional
            null,
            // Origin data: Optional
            null,
            ),
            this
        )

        // Handle user signing in when app is not alive
        web3Auth.setResultUrl(intent?.data)

        // Call initialize() in onCreate() to check for any existing session.
        val sessionResponse: CompletableFuture<Void> = web3Auth.initialize()

        sessionResponse.whenComplete { _, error ->
            if (error == null) {

                println("PrivKey: " + web3Auth.getPrivkey())
                println("ed25519PrivKey: " + web3Auth.getEd25519PrivKey())
                println("Web3Auth UserInfo" + web3Auth.getUserInfo())
                credentials = Credentials.create(web3Auth.getPrivkey())
                web3 = Web3j.build(HttpService(rpcUrl))
             } else {
                Log.d("MainActivity_Web3Auth", error.message ?: "Something went wrong")
                // Ideally, you should initiate the login function here.
            }
        }




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