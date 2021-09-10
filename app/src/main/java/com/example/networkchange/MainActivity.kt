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

    override fun performNetworkOperation(){
        Toast.makeText(this,"Perform service request", Toast.LENGTH_SHORT).show()
    }

    override fun noInternetConnected(){
        Toast.makeText(this,"No internet connection", Toast.LENGTH_SHORT).show()
    }
}