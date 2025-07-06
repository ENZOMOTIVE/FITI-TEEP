package com.example.fiti_teep.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val bottom_navigation_Items = listOf(
    BottomNavigationItem(
        title = "Home",
        icon = Icons.Filled.Home,
        route = Routes.HOME
    ),
    BottomNavigationItem(
        title = "AI Chat",
        icon = Icons.Filled.Email,
        route = Routes.CHAT
    )
)