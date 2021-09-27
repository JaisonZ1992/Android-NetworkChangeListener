package com.jaisonz.androidnetworkchangelistener

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity


object NetworkUtil {

    private var networkCallback: NetworkConnectivityCallback? = null

    @Suppress("Deprecation")
    fun isNetworkConnected(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            capabilities?.let {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
                        NetworkCapabilities.TRANSPORT_CELLULAR
                    )
                ) {
                    result = true
                }
            }
        } else {
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) {
                // connected to the internet
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    result = true
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    result = true
                }
            }
        }
        return result
    }

    fun registerForConnectivityChanges(
        context: Context,
        networkResultCallback: NetworkResultCallback
    ) {
        val currentState = isNetworkConnected(context)
        networkCallback = NetworkConnectivityCallback(currentState, networkResultCallback)
        registerNetworkCallback(context)
    }

    private fun registerNetworkCallback(context: Context) {
        val connectivityManager =
            context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            networkCallback?.let { connectivityManager.registerDefaultNetworkCallback(it) }
        } else {
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
            networkCallback?.let { connectivityManager.registerNetworkCallback(request, it) }
        }
    }

    fun unregisterNetworkChangeListener(context: Context) {
        networkCallback?.let {
            val connectivityManager =
                context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.unregisterNetworkCallback(it)
        }
        networkCallback = null
    }
}
