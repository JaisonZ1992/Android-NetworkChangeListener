package com.example.networkchange

import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : BaseActivity() {

    companion object{
        val TAG = MainActivity::class.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun performNetworkOperation() {
        Log.d(TAG, "Perform service request")
    }

    override fun noInternetConnected() {
        Log.d(TAG, "No internet connection")
    }
}