package com.example.fiti_teep.ui.screens.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fiti_teep.R
import com.example.fiti_teep.ui.screens.userCard.UserProfileCard


@Composable
fun AccScreen(paddingValues: PaddingValues) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Column {

            Text(
            text = "Welcome to Account Screen",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            color = Color(0xFF00BF63)
            )
            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}