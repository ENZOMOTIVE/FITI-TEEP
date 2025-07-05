package com.example.fiti_teep.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiti_teep.ui.theme.FititeepTheme
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fiti_teep.R
import com.example.fiti_teep.ui.navigation.Routes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {


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
                        contentDescription = "App Logo",

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center

        ) {
            Column(
            horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(text = "Chat With our AI",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold )

                Spacer(modifier = Modifier.padding(4.dp))

                Button(onClick = {navController.navigate(Routes.CHAT) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00BF63)
                    )

                ) {
                    Text(text = "Chat Room")
                }

            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val previewnavController = rememberNavController()
    FititeepTheme {
        HomeScreen(previewnavController)
    }
}