package com.kinecut.app

import android.app.Application

class KineCutApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: KineCutApplication
            private set
    }
}
