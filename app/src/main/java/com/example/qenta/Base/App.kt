package com.example.qenta.Base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.example.qenta.Utils.LocaleUtils

@SuppressLint("Registered")
class App: Application() {

    companion object {
    var mContext: Context?= null
    }

    override fun attachBaseContext(base: Context?) {
        if (base == null) super.attachBaseContext(base)
        else super.attachBaseContext(LocaleUtils.onAttach(base, LocaleUtils.EN_LANGUAGE))
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

    }
}