package com.example.fiti_teep.ui.Screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fiti_teep.ui.theme.FititeepTheme
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("PetCare App") }
            )
        }
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
                Text(text = "Chat With our AI")

                Spacer(modifier = Modifier.padding(4.dp))

                Button(onClick = { }) {
                    Text(text = "Chat Room")
                }

            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    FititeepTheme {
        HomeScreen()
    }
}