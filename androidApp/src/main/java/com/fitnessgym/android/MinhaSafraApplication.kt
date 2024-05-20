package com.fitnessgym

import android.app.Application
import com.fitnessgym.android.di.databaseModule
import com.fitnessgym.android.di.viewModelModule
import com.fitnessgym.di.sharedKoinModule
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