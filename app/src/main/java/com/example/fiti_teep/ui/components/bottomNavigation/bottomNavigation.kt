package com.example.fiti_teep.ui.components.bottomNavigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiti_teep.ui.navigation.BottomNavigationItem

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomNavigationItem>,
    currentRoute: String?
) {
    NavigationBar(
        modifier = Modifier.height(75.dp),
        containerColor = Color(0xFFD9FBEA),
        tonalElevation = 3.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(34.dp).padding(top =10.dp)

                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                selected = item.route == currentRoute,
                onClick = {
                    if (item.route != currentRoute) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF00BF63),
                    selectedTextColor = Color(0xFF00BF63),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
