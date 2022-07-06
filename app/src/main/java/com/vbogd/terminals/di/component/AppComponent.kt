package com.vbogd.terminals.di.component

import android.content.Context
import com.vbogd.terminals.di.module.AppModule
import com.vbogd.terminals.di.module.DatabaseModule
import com.vbogd.terminals.di.module.NetworkModule
import com.vbogd.terminals.di.module.ViewModelModule
import com.vbogd.terminals.presentation.screen.direction.DirectionFragment
import com.vbogd.terminals.presentation.screen.terminals.TerminalsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    // fragments
    fun inject(directionFragment: DirectionFragment)
    fun inject(terminalsFragment: TerminalsFragment)
}