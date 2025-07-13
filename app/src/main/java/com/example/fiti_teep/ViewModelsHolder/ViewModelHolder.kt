package com.example.fiti_teep.ViewModelsHolder

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.fiti_teep.ui.screens.chat.ChatViewModel
import com.example.fiti_teep.ui.screens.login.LoginViewModel

data class ViewModelHolder(
    val loginViewModel: LoginViewModel,
    val chatViewModel: ChatViewModel
)

// Container to hold ViewModel Instances

fun provideAppViewModel (owner: ViewModelStoreOwner): ViewModelHolder{
return ViewModelHolder(
    chatViewModel = ViewModelProvider(owner).get(ChatViewModel::class),
    loginViewModel = ViewModelProvider(owner).get(LoginViewModel::class)
)
}