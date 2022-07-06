package com.vbogd.terminals

import android.app.Application
import com.vbogd.terminals.di.component.AppComponent
import com.vbogd.terminals.di.component.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}