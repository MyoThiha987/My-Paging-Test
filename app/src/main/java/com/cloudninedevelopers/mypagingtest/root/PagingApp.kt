package com.cloudninedevelopers.mypagingtest.root

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PagingApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}