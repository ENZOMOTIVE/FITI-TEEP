package com.example.fiti_teep.koin_di.chatMessage_module

import com.example.fiti_teep.ui.screens.chat.ChatRepository
import com.example.fiti_teep.ui.screens.chat.ChatViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val chatMessage_module = module {
    single{
        ChatRepository(get())
    }
    viewModel { ChatViewModel(get()) }

}