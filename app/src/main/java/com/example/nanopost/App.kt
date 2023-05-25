package com.example.nanopost

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {
    companion object {
        lateinit var instance: App
    }
}