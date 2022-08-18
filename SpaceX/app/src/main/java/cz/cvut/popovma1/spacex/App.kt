package cz.cvut.popovma1.spacex

import android.app.Application
import cz.cvut.popovma1.spacex.di.rocketLaunchModule
import cz.cvut.popovma1.spacex.di.rocketModule
import cz.cvut.popovma1.spacex.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(modules = sharedModule + rocketModule + rocketLaunchModule)
        }
    }
}
