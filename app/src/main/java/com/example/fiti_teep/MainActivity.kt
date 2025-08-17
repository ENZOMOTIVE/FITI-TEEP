package com.example.fiti_teep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.fiti_teep.ui.screens.login.LoginScreen
import com.example.fiti_teep.ui.screens.login.LoginViewModel
import com.example.fiti_teep.ui.theme.FititeepTheme

class MainActivity : ComponentActivity() {

    private val loginViewModel = LoginViewModel()


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FititeepTheme {
                val navController = rememberNavController()

                val isLoggedIn by loginViewModel.isLoggedIn

                if (isLoggedIn) {
                    Pawpulse(navController)
                } else {
                    LoginScreen(loginViewModel)
                }


            }
        }
    }
}