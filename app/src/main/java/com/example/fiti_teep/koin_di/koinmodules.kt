package com.example.fiti_teep.koin_di

import com.example.fiti_teep.koin_di.chatMessage_module.chatMessage_module
import com.example.fiti_teep.koin_di.ktor_network_module.ktor_httpclient_module

// Centralised place to keep all the modules
val appModules = listOf(
    ktor_httpclient_module,
    chatMessage_module



)