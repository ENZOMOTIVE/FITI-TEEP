package com.example.fiti_teep.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UpcomingAppointmentsCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5FFF9))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "📅 Upcoming Appointments",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color(0xFF00BF63)
            )

            Spacer(modifier = Modifier.height(12.dp))

            AppointmentItem(
                title = "Vet Checkup",
                date = "July 12, 2025"
            )
            Spacer(modifier = Modifier.height(10.dp))
            AppointmentItem(
                title = "Vaccination",
                date = "Aug 1, 2025"
            )
        }
    }
}

@Composable
fun AppointmentItem(title: String, date: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "🐾 $title", fontWeight = FontWeight.Medium, fontSize = 15.sp, color = Color.Black)
            Text(text = date, fontSize = 13.sp, color = Color.Gray)
        }
    }
}
