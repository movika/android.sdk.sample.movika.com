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
            apiKey = "your_api_key",
            appName = "your_package_name",
            appVersion = "1.6.1",
        )
        MovikaSdk.setup(initConfig, this)
    }
}
