package com.example.multilanguagesupport

import android.app.Application
import android.content.Context
import android.content.res.Configuration

class MultiLanguageApp : Application() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocationManager .setLocale(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocationManager .setLocale(this)
    }
}