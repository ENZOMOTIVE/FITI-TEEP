package com.example.fiti_teep.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fiti_teep.ui.Screens.HomeScreen


@Composable
fun NavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {


        composable("home"){
            HomeScreen()
        }
    }


}