package com.jaisonz.androidnetworkchangelistener

import android.net.ConnectivityManager
import android.net.Network

class NetworkConnectivityCallback(
    private var currentState: Boolean,
    private val networkResultCallback: NetworkResultCallback
) : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        if (!currentState) {
            currentState = true
            networkResultCallback.connected()
        }
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        if (currentState) {
            currentState = false
            networkResultCallback.disconnected()
        }
    }
}