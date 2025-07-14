package com.example.fiti_teep.ui.screens.webAuth

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fiti_teep.ui.navigation.Routes
import com.web3auth.core.Web3Auth
import com.web3auth.core.types.ExtraLoginOptions
import com.web3auth.core.types.LoginParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.web3auth.core.types.Provider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color


@Composable
fun Web3AuthScreen(
    navController: NavController,
    web3Auth: Web3Auth
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Web3Auth Sign In",
                style = MaterialTheme.typography.headlineSmall
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Enter Email") },
                singleLine = true
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        isLoading = true
                        errorMessage = null
                        try {
                            val loginParams = LoginParams(
                                loginProvider = Provider.EMAIL_PASSWORDLESS,
                                extraLoginOptions = ExtraLoginOptions(login_hint = email)
                            )
                            val result = withContext(Dispatchers.IO) {
                                web3Auth.login(loginParams).get()
                            }

                            Log.d("Web3Auth", "Login Success: ${result.userInfo}")
                            isLoading = false

                            // Navigate to HOME on success
                            navController.navigate(Routes.HOME) {
                                popUpTo(Routes.WEB3AUTH) { inclusive = true }
                            }

                        } catch (e: Exception) {
                            isLoading = false
                            errorMessage = "Login failed: ${e.message}"
                            Log.e("Web3Auth", "Login Error", e)
                        }
                    }
                },
                enabled = !isLoading
            ) {
                Text(if (isLoading) "Logging in..." else "Login Web3Auth")
            }

            Button(
                onClick = {navController.navigate(Routes.HOME)}
            ) {
                Text("Skip Login")
            }

            errorMessage?.let {
                Text(text = it, color = Color.Red)
            }
        }
    }
}
