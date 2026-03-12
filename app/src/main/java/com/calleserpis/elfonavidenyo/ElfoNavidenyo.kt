package com.calleserpis.elfonavidenyo

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ElfoNavidenyo : Application() {

    override fun onCreate() {
        super.onCreate()
        // Inicialización global del SDK de anuncios
        MobileAds.initialize(this)
    }
}