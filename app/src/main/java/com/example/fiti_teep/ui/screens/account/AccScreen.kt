package com.example.fiti_teep.ui.screens.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.web3auth.core.Web3Auth

@Composable
fun AccScreen(
    paddingValues: PaddingValues,
    web3Authinstance: Web3Auth

    ) {


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Column {


            Spacer(modifier = Modifier.height(24.dp))


            InfoItem(label = "Wallet Private Key", value = web3Authinstance.getPrivateKey())
            InfoItem(label = "Ed25519 Private Key", value = web3Authinstance.getEd25519PrivateKey())
            InfoItem(label = "Balance", value = "0.001 ETH")

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {  },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE63946), // red matching theme
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(24.dp) // pill-style rounded corners
            ) {
                Text("Logout")
            }

        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Heading
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(6.dp))

        // Container for value
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF3F3F3),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp)
        ) {
            Text(text = value, fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
