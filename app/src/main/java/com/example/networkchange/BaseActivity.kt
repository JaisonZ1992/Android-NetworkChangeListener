package com.example.networkchange

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.jaisonz.androidnetworkchangelistener.NetworkResultCallback
import com.jaisonz.androidnetworkchangelistener.NetworkUtil

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performActionBasedOnNetworkStatus()
    }

    override fun onStart() {
        super.onStart()
        registerForNetworkStateChanges()
    }

    override fun onStop() {
        super.onStop()
        unregisterNetworkListener()
    }

    protected open fun performNetworkOperation(){
        //Override and implement this wherever required
    }

    protected open fun noInternetConnected(){
        //Override and implement this wherever required
    }

    /**
     * Call API or show no internet dialog based on requirement from on create
     */
    private fun performActionBasedOnNetworkStatus() {
        if (NetworkUtil.isNetworkConnected(this)) {
            performNetworkOperation()
        }else{
            noInternetConnected()
        }
    }

    /**
     * Register for network changes
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun registerForNetworkStateChanges() {
        NetworkUtil.registerForConnectivityChanges(this, object : NetworkResultCallback {
            override fun connected() {
                performNetworkOperation()
            }

            override fun disconnected() {
                noInternetConnected()
            }
        })
    }

    /**
     * Unregister for network changes
     */
    private fun unregisterNetworkListener() {
        NetworkUtil.unregisterNetworkChangeListener(this)
    }
}