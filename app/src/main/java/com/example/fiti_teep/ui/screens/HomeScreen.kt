package com.example.fiti_teep.ui.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fiti_teep.R
import com.example.fiti_teep.ui.components.AIAssistantPromptBox
import com.example.fiti_teep.ui.components.CareTipsCard
import com.example.fiti_teep.ui.components.PetHealthSummaryCard
import com.example.fiti_teep.ui.components.QuickActionButtonsRow
import com.example.fiti_teep.ui.components.UpcomingAppointmentsCard
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

            Text(
                text = "Quick Assistance",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            PetHealthSummaryCard()

            UpcomingAppointmentsCard()
            CareTipsCard()



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


