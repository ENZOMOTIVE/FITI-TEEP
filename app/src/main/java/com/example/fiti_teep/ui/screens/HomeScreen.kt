package com.example.fiti_teep.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fiti_teep.R
import com.example.fiti_teep.ui.screens.userCard.UserProfileCard



@Composable
fun HomeScreen(navController: NavController, paddingValues: PaddingValues) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Column {

            UserProfileCard(
                name = "Maxx",
                age = "3",
                dob = "12-05-2025",
                imageResId = R.drawable.cat_test_image
            )

            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    // Mock padding and NavController for preview
    val mockNavController = rememberNavController()
    val mockPaddingValues = PaddingValues(0.dp)

    HomeScreen(navController = mockNavController, paddingValues = mockPaddingValues)
}


