package com.example.ft_hangouts

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HangoutsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}