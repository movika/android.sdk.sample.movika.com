package com.movika.sample.application

import android.app.Application
import android.util.Log
import film.interactive.player.sdk.android.InitConfig
import film.interactive.player.sdk.android.MovikaSdk

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("IAPApp", "onCreate: ")
        setupMovikaSdk()
    }

    private fun setupMovikaSdk() {
        val initConfig = InitConfig(
            apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhcHBOYW1lIjoiY29tLm1vdmlrYS5wbGF0Zm9ybSIsImFwcGxpY2F0aW9uX2xpbmsiOiIiLCJpZCI6NDEsInBsYXRmb3JtIjoyfQ.E-TtvE2eWie3zk6xJs0xhbWNX6okGYEO1f0aI48a8uA",
            appName = "com.movika.platform",
            appVersion = "1.6.1",
        )
        MovikaSdk.setup(initConfig, this)
    }
}
