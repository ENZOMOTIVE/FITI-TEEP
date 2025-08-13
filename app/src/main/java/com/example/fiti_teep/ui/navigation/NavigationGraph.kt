package com.example.fiti_teep.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fiti_teep.ui.screens.chat.ChatScreen
import com.example.fiti_teep.ui.screens.HomeScreen
import com.example.fiti_teep.ui.screens.chat.ChatViewModel
import com.example.fiti_teep.ui.screens.login.LoginScreen


@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,

){

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {


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
            val chatViewModel: ChatViewModel = viewModel()
            ChatScreen(
                paddingValues,
                viewModel = chatViewModel
            )

        }




    }


}