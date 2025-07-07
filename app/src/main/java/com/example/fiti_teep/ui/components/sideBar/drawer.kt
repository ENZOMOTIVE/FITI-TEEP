package com.example.fiti_teep.ui.components.sideBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fiti_teep.R


@Composable
fun DrawerContent() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(150.dp)

    ) {

        Surface(
            tonalElevation = 4.dp,
            shadowElevation = 4.dp,
            color = Color(0xFF00BF63),
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)



        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 34.dp, start = 14.dp, end = 14.dp, bottom = 14.dp)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_app_logo_test),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 4.dp)
                )

            }
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            DrawerItem("Profile")
            DrawerItem("Add Pet")
            DrawerItem("Settings")
            DrawerItem("Logout")
        }
    }
}

@Composable
fun DrawerItem(label: String, onClick: () -> Unit = {}) {
    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDrawerContent() {
    MaterialTheme {
        DrawerContent()
    }
}