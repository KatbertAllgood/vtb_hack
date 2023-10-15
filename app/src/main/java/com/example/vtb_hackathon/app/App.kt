package com.example.vtb_hackathon.app

import android.app.Application
import com.example.vtb_hackathon.data.utils.Constants
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey(Constants.API_MAP_KEY)
    }
}