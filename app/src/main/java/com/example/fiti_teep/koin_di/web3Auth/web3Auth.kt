package com.example.fiti_teep.koin_di.web3Auth

import androidx.core.net.toUri
import com.web3auth.core.Web3Auth
import com.web3auth.core.types.BuildEnv
import com.web3auth.core.types.Network
import com.web3auth.core.types.Web3AuthOptions
import org.koin.dsl.module

val web3AuthModule = module {
    single {
        Web3Auth(
            Web3AuthOptions(
                // Client id of Web3Auth client id
                "BIYP2ZZSe0kfk9GGUT_ruqFI6wj28jQ0LdclMaoYUd7XeVbg39fJ9ue5ICpa7qnB3EHkyL9fBCuP9Y4-hgB0Zuk",
                // Blockchain Network
                Network.SAPPHIRE_DEVNET,
                // Production Enviorenment
                BuildEnv.PRODUCTION,
                // Redirect Url : The Login Redirect Url
                "com.example.fiti_teep://auth".toUri(),
                // Web3Auth SDK URL
                "",
                // White Label: Optional UI Customization
                null,
                // Login Config: Optional login method configuration
                null,
                // useCoreKitKey: Optional Boolean, advanced use
                null,
                // Chain Name Space: Optional chain selection, e.g., EVM or Solana
                null,
                //  Multi factor Authorization: Optional Configuration
                null,
                // Session time: optional
                null,
                // Wallet Sdk Url: optional
                null,
                //dashboard url: optional
                null,
                // Origin data: Optional
                null,
            ),
            get()
        )
    }
}