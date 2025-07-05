package com.example.fiti_teep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.fiti_teep.ui.navigation.NavGraph
import com.example.fiti_teep.ui.theme.FititeepTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.fiti_teep.R

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FititeepTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "PetCare App",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            navigationIcon = {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_app_logo_test),
                                    contentDescription = "App Logo"
                                )
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color(0xFF00BF63),
                                titleContentColor = Color.White
                            )
                        )
                    },
                    containerColor = Color.White
                ) { paddingValues ->
                    NavGraph(navController = navController, paddingValues = paddingValues)
                }
            }
        }
    }
}
