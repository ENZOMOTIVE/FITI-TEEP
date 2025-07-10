package com.example.fiti_teep.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HealthReportCard() {
    Column(
        modifier = Modifier
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFE0E0E0), shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Health Rating
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Favorite, contentDescription = "Health", tint = Color.Red)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Health Rating", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.weight(1f))
            Row {
                repeat(3) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFC107))
                }

            }
        }
        Text("6.5/10", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(vertical = 8.dp))

        Divider(color = Color(0xFFEEEEEE), thickness = 1.dp)

        Spacer(modifier = Modifier.height(12.dp))

        // Diagnosis
        Text("Diagnosis", style = MaterialTheme.typography.titleMedium)
        Text("Fungal Dermatitis", fontWeight = FontWeight.Bold)
        Text("Confidence: 87%", color = Color.Gray)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Observed Symptoms:", fontWeight = FontWeight.SemiBold)
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            listOf(
                "Red, inflamed skin patches",
                "Circular lesions with raised borders",
                "Hair loss in affected areas",
                "Possible itching and discomfort"
            ).forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = Color(0xFFFFA000))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(it)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Treatment Recommendations
        Text("Treatment Recommendations", fontWeight = FontWeight.SemiBold)
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            listOf(
                "Apply antifungal topical medication",
                "Keep affected area clean and dry",
                "Prevent scratching with protective collar",
                "Schedule veterinary consultation within 3–5 days",
                "Monitor for spread to other areas"
            ).forEach {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF4CAF50))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(it)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            "Follow-up: Monitor progress and reassess in 1–2 weeks.",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF1976D2),
            fontWeight = FontWeight.Medium
        )
    }
}
