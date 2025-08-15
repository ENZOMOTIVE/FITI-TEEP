package com.example.fiti_teep.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fiti_teep.ui.screens.chat.ChatScreen
import com.example.fiti_teep.ui.screens.HomeScreen



@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,

){

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        composable(Routes.HOME){
            HomeScreen(navController, paddingValues)
        }

        composable(Routes.CHAT){
            ChatScreen(
                paddingValues

            )

        }




    }


}