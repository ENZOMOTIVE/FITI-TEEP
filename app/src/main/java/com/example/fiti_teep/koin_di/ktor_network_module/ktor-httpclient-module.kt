package com.example.fiti_teep.koin_di.ktor_network_module

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val ktor_httpclient_module = module {
    single {
        HttpClient(CIO)
    }
}