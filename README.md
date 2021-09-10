# NetworkChangeListener

This project demonstrates how to handle network state changes from connected to disconnected. It supports from API Level 21 - 30.

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.JaisonZ1992:Android-NetworkChangeListener:v1.0.0'
	}

Step 3

Use the following method in the BaseActivity

	NetworkUtil.registerForConnectivityChanges(this, object : NetworkResultCallback {
		    override fun connected() {
			//Perform network operation 
		    }

		    override fun disconnected() {
			//Show no internet dialog
		    }
		})
    
[![](https://jitpack.io/v/JaisonZ1992/Android-NetworkChangeListener.svg)](https://jitpack.io/#JaisonZ1992/Android-NetworkChangeListener)

