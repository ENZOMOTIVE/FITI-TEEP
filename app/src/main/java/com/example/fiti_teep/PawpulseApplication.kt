package com.example.fiti_teep

import android.app.Application
import com.example.fiti_teep.koin_di.chatMessage_module.chatMessage_module
import com.example.fiti_teep.koin_di.ktor_network_module.ktor_httpclient_module
//import com.example.fiti_teep.koin_di.ktor_network_module.ktor_httpclient_module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PawpulseApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@PawpulseApplication)

        // Load modules
           modules(ktor_httpclient_module,
               chatMessage_module)
        }


    }
}