package com.example.fiti_teep


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.fiti_teep.ui.theme.FititeepTheme
import com.example.fiti_teep.ui.screens.login.LoginScreen
import com.example.fiti_teep.ui.screens.login.LoginViewModel


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