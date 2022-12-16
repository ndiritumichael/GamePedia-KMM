package com.devmike.gamepedia.android

import android.app.Application
import com.devmike.gamepedia.di.debugBuild
import com.devmike.gamepedia.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class GamePediaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(enableNetworkLogs = BuildConfig.DEBUG) {
            androidLogger(level = if (BuildConfig.DEBUG) Level.DEBUG else Level.NONE)
            androidContext(androidContext = this@GamePediaApplication)
        }
        if (BuildConfig.DEBUG) debugBuild()
    }
}
