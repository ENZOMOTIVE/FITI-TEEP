package com.example.fiti_teep.ui.screens.userCard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fiti_teep.R

@Composable
fun UserProfileCard(
    name: String,
    age: String,
    dob: String,
    imageResId: Int,
    onProfileViewClick: () -> Unit = {}
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .height(210.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Profile Info Section
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "User Image",
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(20.dp))

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "Name: $name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color(0xFF00BF63)
                    )
                    Text(
                        text = "Age: $age",
                        fontSize = 19.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "DOB: $dob",
                        fontSize = 19.sp,
                        color = Color.Gray
                    )
                }
            }

            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

            // Bottom Clickable Bar
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onProfileViewClick() }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "View Full Profile",
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color(0xFF00BF63)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfileCard() {
    UserProfileCard(
        name = "Maxx",
        age = "3",
        dob = "12-05-2025",
        imageResId = R.drawable.cat_test_image
    )
}
