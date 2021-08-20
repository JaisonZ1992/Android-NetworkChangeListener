package com.example.networkchange

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity


object NetworkUtil{

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
        networkResultCallback: NetworkResultCallback){
        //If mobile data and WIFI is enabled you will get callback in the following order when WIFI is turned off
        // WIFI Off -> onLost -> onAvailable. The connection is getting reset to mobile data from WIFI
        val networkCallback: ConnectivityManager.NetworkCallback = object : ConnectivityManager.NetworkCallback() {
            var currentState = isNetworkConnected(context)

            override fun onAvailable(network: Network) {
                val status = isNetworkConnected(context)
                //Check if network enabled and the status has changed from no-internet to connected
                if(status && status != currentState){
                    currentState = true
                    networkResultCallback.connected()
                }
            }
            override fun onLost(network: Network) {
                val status = isNetworkConnected(context);
                //Check if network disabled and the status has changed from connected to no-internet
                if (!status && status != currentState) {
                    currentState = false
                    networkResultCallback.disconnected()
                }
            }
        }

        val connectivityManager = context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()
            connectivityManager.registerNetworkCallback(request, networkCallback)
        }
    }
}
