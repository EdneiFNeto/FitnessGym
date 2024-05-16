package com.minhasafra360.android

import android.app.Application
import com.minhasafra360.android.di.databaseModule
import com.minhasafra360.android.di.viewModelModule
import com.minhasafra360.di.sharedKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MinhaSafraApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val module = sharedKoinModule + viewModelModule + databaseModule
        startKoin {
            androidContext(this@MinhaSafraApplication)
            modules(module)
        }
    }
}