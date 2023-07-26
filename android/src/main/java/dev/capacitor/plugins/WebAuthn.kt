package dev.capacitor.plugins

import android.app.Activity
import android.os.Build.VERSION_CODES.P
import android.util.Log
import androidx.credentials.CreateCredentialResponse
import androidx.credentials.CreatePublicKeyCredentialRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.GetPublicKeyCredentialOption
import androidx.credentials.PublicKeyCredential
import androidx.credentials.exceptions.CreateCredentialCancellationException
import androidx.credentials.exceptions.CreateCredentialException
import androidx.credentials.exceptions.CreateCredentialInterruptedException
import androidx.credentials.exceptions.CreateCredentialProviderConfigurationException
import androidx.credentials.exceptions.CreateCredentialUnknownException
import androidx.credentials.exceptions.CreateCustomCredentialException
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.exceptions.publickeycredential.CreatePublicKeyCredentialDomException
import com.getcapacitor.JSObject
import com.getcapacitor.PluginCall
import org.json.JSONException


class WebAuthn {
    private lateinit var credentialManager: CredentialManager
    private lateinit var activity: Activity
    companion object {
        val TAG: String = "WebAuthn"
    }

    fun init(activity: Activity) {
        this.activity = activity
        this.credentialManager = CredentialManager.create(activity)
    }

    fun isWebAuthnAvailable(): Boolean {
        return android.os.Build.VERSION.SDK_INT >= P
    }

    suspend fun create(call: PluginCall) {
        val requestJson: String = call.data.toString()
        val request = CreatePublicKeyCredentialRequest(requestJson)
        try {
            val response = credentialManager.createCredential(request, activity)
            handleCreateCredentialSuccess(call, response)
        } catch (e: CreateCredentialException) {
            handleCreateCredentialFailure(call, e)
        }
    }

    suspend fun get(call: PluginCall) {
        val requestJson: String = call.data.toString()
        val publicKeyCredentialOption = GetPublicKeyCredentialOption(requestJson);
        val request = GetCredentialRequest(listOf(publicKeyCredentialOption))
        try {
            val response = credentialManager.getCredential(request, activity)
            handleGetCredentialSuccess(call, response)
        } catch (e: GetCredentialException) {
            Log.e(TAG, "getCredential failed with exception: " + e.message.toString())
            call.reject("An error occurred while getting a credential, please check logs for additional details.", e)
        }
    }

    private fun handleCreateCredentialSuccess(
        call: PluginCall,
        response: CreateCredentialResponse
    ) {
        try {
            val ret = JSObject(response.data.getString(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL))
            call.resolve(ret)
        } catch (e: JSONException) {
            Log.e(TAG, "get failed with exception: " + e.message.toString())
            call.reject("An error occurred while parsing a credential response, please check logs for additional details.", e)
        }
    }

    private fun handleGetCredentialSuccess(
        call: PluginCall,
        response: GetCredentialResponse
    ) {
        try {
            if (response.credential is PublicKeyCredential) {
                val responseJson = (response.credential as PublicKeyCredential).authenticationResponseJson
                call.resolve(JSObject(responseJson))
            } else {
                call.reject("The credential type is not PublicKeyCredential")
            }
        } catch (e: JSONException) {
            Log.e(TAG, "get failed with exception: " + e.message.toString())
            call.reject("An error occurred while parsing a credential response, please check logs for additional details.", e)
        }

    }

    // These are types of errors that can occur during passkey creation.
    private fun handleCreateCredentialFailure(call: PluginCall, e: CreateCredentialException) {
        val msg = when (e) {
            is CreatePublicKeyCredentialDomException -> {
                // Handle the passkey DOM errors thrown according to the
                // WebAuthn spec using e.domError
                "An error occurred while creating a passkey, please check logs for additional details."
            }
            is CreateCredentialCancellationException -> {
                // The user intentionally canceled the operation and chose not
                // to register the credential.
                "The user intentionally canceled the operation and chose not to register the credential. Check logs for additional details."
            }
            is CreateCredentialInterruptedException -> {
                // Retry-able error. Consider retrying the call.
                "The operation was interrupted, please retry the call. Check logs for additional details."
            }
            is CreateCredentialProviderConfigurationException -> {
                // Your app is missing the provider configuration dependency.
                // Most likely, you're missing "credentials-play-services-auth".
                "Your app is missing the provider configuration dependency. Check logs for additional details."
            }
            is CreateCredentialUnknownException -> {
                "An unknown error occurred while creating passkey. Check logs for additional details."
            }
            is CreateCustomCredentialException -> {
                // You have encountered an error from a 3rd-party SDK. If you
                // make the API call with a request object that's a subclass of
                // CreateCustomCredentialRequest using a 3rd-party SDK, then you
                // should check for any custom exception type constants within
                // that SDK to match with e.type. Otherwise, drop or log the
                // exception.
                "An unknown error occurred from a 3rd party SDK. Check logs for additional details."
            }
            else -> {
                Log.w(TAG, "Unexpected exception type ${e::class.java.name}")
                "An unknown error occurred."
            }
        }
        Log.e(TAG, "createPasskey failed with exception: " + e.message.toString())
        call.reject(msg)
    }
}