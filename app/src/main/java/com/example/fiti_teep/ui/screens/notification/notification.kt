package com.example.fiti_teep.ui.screens.notification

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun NotificationDropdown() {
    var expanded by remember { mutableStateOf(false) }

    Box {
        IconButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notifications",
                tint = Color.White,
                modifier = Modifier.size(33.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(205.dp)
                .height(180.dp)
        ) {
            DropdownMenuItem(
                text = { Text("No new notifications", fontSize = 19.sp, color = Color.Gray) },
                onClick = { expanded = false }
            )

            DropdownMenuItem(
                text = { Text("Test Notification 1", fontSize = 19.sp , color = Color.Gray) },
                onClick = { expanded = false }
            )

            DropdownMenuItem(
                text = { Text("Test Notification 2", fontSize = 19.sp, color = Color.Gray) },
                onClick = { expanded = false }
            )

            DropdownMenuItem(
                text = { Text("Test Notification 3", fontSize = 19.sp , color = Color.Gray) },
                onClick = { expanded = false }
            )
        }
    }
}



