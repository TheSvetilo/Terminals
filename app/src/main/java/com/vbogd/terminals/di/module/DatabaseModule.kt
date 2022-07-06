package com.vbogd.terminals.di.module

import android.content.Context
import androidx.room.Room
import com.vbogd.terminals.data.AppDatabase
import com.vbogd.terminals.data.OrderManager
import com.vbogd.terminals.data.orderRepository.local.OrderLocalDataSource
import com.vbogd.terminals.data.terminalRepository.TerminalsRepositoryImpl
import com.vbogd.terminals.data.terminalRepository.local.TerminalLocalDataSource
import com.vbogd.terminals.data.terminalRepository.remote.TerminalRemoteDataSource
import com.vbogd.terminals.domain.repository.OrdersRepository
import com.vbogd.terminals.domain.repository.TerminalsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "Orders.db"
        ).build()
    }

}