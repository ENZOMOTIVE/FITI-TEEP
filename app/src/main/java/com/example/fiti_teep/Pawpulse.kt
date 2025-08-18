package com.example.fiti_teep


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.fiti_teep.ui.components.bottomNavigation.BottomNavigationBar
import com.example.fiti_teep.ui.components.sideBar.DrawerContent
import com.example.fiti_teep.ui.navigation.NavGraph
import com.example.fiti_teep.ui.navigation.bottom_navigation_Items
import com.example.fiti_teep.ui.screens.notification.NotificationDropdown
import com.web3auth.core.Web3Auth
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pawpulse(navController: NavHostController, web3Auth: Web3Auth) {
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
                            text = "Pawpulse",
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
                    },
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
            NavGraph(navController = navController, paddingValues = paddingValues, web3Authinstance = web3Auth)

        }
    }
}