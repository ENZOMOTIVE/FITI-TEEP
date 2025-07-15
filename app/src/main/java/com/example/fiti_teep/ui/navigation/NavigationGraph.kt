package com.example.fiti_teep.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fiti_teep.ViewModelsHolder.ViewModelHolder
import com.example.fiti_teep.ui.screens.chat.ChatScreen
import com.example.fiti_teep.ui.screens.HomeScreen
import com.example.fiti_teep.ui.screens.login.LoginScreen
import com.example.fiti_teep.ui.screens.webAuth.Web3AuthScreen
import com.web3auth.core.Web3Auth


@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewmodel: ViewModelHolder,
    web3Authparam: Web3Auth,

    ){

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        composable(Routes.WEB3AUTH){
             Web3AuthScreen(navController,web3Authparam)

        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginScreenSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }


        composable(Routes.HOME){
            HomeScreen(navController, paddingValues)
        }

        composable(Routes.CHAT){
            ChatScreen(paddingValues, viewModel = viewmodel.chatViewModel)
        }




    }


}