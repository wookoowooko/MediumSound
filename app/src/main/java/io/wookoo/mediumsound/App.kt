package io.wookoo.mediumsound

import android.app.Application
import io.wookoo.mediumsound.core.audiomanager.di.appAudioManagerModule
import io.wookoo.mediumsound.core.settings.di.dataStoreModule
import io.wookoo.mediumsound.first.screen.firstScreenModule
import io.wookoo.mediumsound.second.screen.secondScreenModule
import io.wookoo.mediumsound.settings.screen.settingsScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(
                firstScreenModule,
                dataStoreModule,
                appAudioManagerModule,
                secondScreenModule,
                settingsScreenModule
            )
        }
    }
}