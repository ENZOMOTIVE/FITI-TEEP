package com.example.fiti_teep

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.fiti_teep.ViewModelsHolder.ViewModelHolder
import com.example.fiti_teep.ViewModelsHolder.provideAppViewModel
import com.example.fiti_teep.ui.components.bottomNavigation.BottomNavigationBar
import com.example.fiti_teep.ui.components.sideBar.DrawerContent
import com.example.fiti_teep.ui.navigation.bottom_navigation_Items
import com.example.fiti_teep.ui.screens.notification.NotificationDropdown
import com.web3auth.core.Web3Auth
import com.web3auth.core.types.BuildEnv
import com.web3auth.core.types.Network
import com.web3auth.core.types.Web3AuthOptions
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    private lateinit var viewModelHolder: ViewModelHolder

    private lateinit var web3Auth: Web3Auth



    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModelHolder = provideAppViewModel(this)

        web3Auth = Web3Auth(
            Web3AuthOptions(
                clientId = "BPi5PB_UiIZ-cPz1GtV5i1I2iOSOHuimiXBI0e-Oe_u6X3oVAbCiAZOTEBtTXw4tsluTITPqA8zMsfxIKMjiqNQ",
                network = Network.SAPPHIRE_MAINNET,
                buildEnv = BuildEnv.PRODUCTION,
                redirectUrl = Uri.parse("com.sbz.web3authdemoapp://auth")
            ), this
        )





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
                        NavGraph(navController = navController, paddingValues = paddingValues, viewmodel = viewModelHolder, web3Authparam = web3Auth )

                    }
                }
            }
        }
    }
}