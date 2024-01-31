package dev.maxsiomin.testdoorscameras

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.maxsiomin.testdoorscameras.util.isDebug
import io.realm.kotlin.Realm
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (isDebug()) {
            Timber.plant(Timber.DebugTree())
        }
    }
}