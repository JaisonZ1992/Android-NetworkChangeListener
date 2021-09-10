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
	        implementation 'com.github.JaisonZ1992:Android-NetworkChangeListener:v1.0.1'
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


	   Copyright 2021 Jaison Zachariah

	   Licensed under the Apache License, Version 2.0 (the "License");
	   you may not use this file except in compliance with the License.
	   You may obtain a copy of the License at

	       http://www.apache.org/licenses/LICENSE-2.0

	   Unless required by applicable law or agreed to in writing, software
	   distributed under the License is distributed on an "AS IS" BASIS,
	   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	   See the License for the specific language governing permissions and
	   limitations under the License.

