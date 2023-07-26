package dev.joyid.app

import android.os.Bundle
import com.getcapacitor.BridgeActivity
import dev.joyid.app.plugin.WebAuthnPlugin

class MainActivity : BridgeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        registerPlugin(WebAuthnPlugin::class.java)
        super.onCreate(savedInstanceState)
    }
}