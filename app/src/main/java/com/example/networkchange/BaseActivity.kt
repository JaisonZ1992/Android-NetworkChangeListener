package com.example.networkchange

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerForNetworkStateChanges()
    }

    protected open fun performNetworkOperation(){
        //Override and implement this wherever required
    }

    protected open fun noInternetConnected(){
        //Override and implement this wherever required
    }

    private fun registerForNetworkStateChanges() {
        if (NetworkUtil.isNetworkConnected(this)) {
            performNetworkOperation()
        }else{
            noInternetConnected()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listenForInternetConnectivity()
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun listenForInternetConnectivity() {
        NetworkUtil.registerForConnectivityChanges(this, object : NetworkResultCallback{
            override fun connected() {
                performNetworkOperation()
            }

            override fun disconnected() {
                noInternetConnected()
            }
        })
    }
}