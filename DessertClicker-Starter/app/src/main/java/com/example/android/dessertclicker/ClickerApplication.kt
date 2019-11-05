package com.example.android.dessertclicker

import android.app.Application
import timber.log.Timber

<<<<<<< HEAD
class ClickerApplication : Application(){
    override fun onCreate(){
        super.onCreate()

=======
class ClickerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
>>>>>>> 11dab097169a269ae91a700b86d51b7523ac173e
        Timber.plant(Timber.DebugTree())
    }
}