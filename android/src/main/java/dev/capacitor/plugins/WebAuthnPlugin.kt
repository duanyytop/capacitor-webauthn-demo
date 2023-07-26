package dev.capacitor.plugins

import androidx.lifecycle.lifecycleScope
import com.getcapacitor.JSObject
import com.getcapacitor.Plugin
import com.getcapacitor.PluginCall
import com.getcapacitor.PluginMethod
import com.getcapacitor.annotation.CapacitorPlugin
import kotlinx.coroutines.launch

@CapacitorPlugin(name = "WebAuthn")
class WebAuthnPlugin : Plugin() {
    private val implementation = WebAuthn()

    override fun load() {
        super.load()
        implementation.init(activity)
    }

    @PluginMethod
    fun isWebAuthnAvailable(call: PluginCall) {
        val ret = JSObject()
        ret.put("value", implementation.isWebAuthnAvailable())
        call.resolve(ret)
    }

    @PluginMethod
    fun create(call: PluginCall) {
        activity.lifecycleScope.launch {
            implementation.create(call)
        }
    }

    @PluginMethod
    fun get(call: PluginCall) {
        activity.lifecycleScope.launch {
            implementation.create(call)
        }
    }
}