package com.example.fiti_teep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.fiti_teep.ui.navigation.NavGraph
import com.example.fiti_teep.ui.theme.FititeepTheme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fiti_teep.R
import com.example.fiti_teep.ui.components.bottomNavigation.BottomNavigationBar
import com.example.fiti_teep.ui.components.sideBar.DrawerContent
import com.example.fiti_teep.ui.navigation.bottom_navigation_Items
import com.example.fiti_teep.ui.screens.notification.NotificationDropdown
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            FititeepTheme {
                val navController = rememberNavController()

                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route

                //Sidebar Drawer
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val coroutineScope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = { DrawerContent() },
                    scrimColor = Color.Transparent
                )
                {
                    Scaffold(
                        topBar = {
                            CenterAlignedTopAppBar(
                                title = {
                                        Text(
                                            text = "PetCare App",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                },
                                navigationIcon = {
                                    IconButton(
                                        onClick = {
                                            coroutineScope.launch { drawerState.open() }
                                        },
                                        modifier = Modifier.size(60.dp)
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
                                ,
                                actions = {
                                    NotificationDropdown()
                                },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = Color(0xFF00BF63),
                                    titleContentColor = Color.White
                                )
                            )

                        },
                        bottomBar = {
                            BottomNavigationBar(
                                navController = navController,
                                items = bottom_navigation_Items,
                                currentRoute = currentRoute
                            )
                        },
                        containerColor = Color.White
                    )

                    { paddingValues ->
                        NavGraph(navController = navController, paddingValues = paddingValues)

                    }
                }
            }
        }
    }
}