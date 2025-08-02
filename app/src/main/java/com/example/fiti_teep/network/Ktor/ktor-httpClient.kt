package com.example.fiti_teep.network.Ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

object HttpProvider{
    val Ktorclient: HttpClient by lazy {
        HttpClient(CIO)
    }
}