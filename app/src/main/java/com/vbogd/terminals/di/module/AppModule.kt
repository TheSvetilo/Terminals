package com.vbogd.terminals.di.module

import com.vbogd.terminals.data.AppDatabase
import com.vbogd.terminals.data.OrderManager
import com.vbogd.terminals.data.orderRepository.local.OrderLocalDataSource
import com.vbogd.terminals.data.terminalRepository.TerminalsRepositoryImpl
import com.vbogd.terminals.data.terminalRepository.local.TerminalLocalDataSource
import com.vbogd.terminals.data.terminalRepository.remote.TerminalApi
import com.vbogd.terminals.data.terminalRepository.remote.TerminalRemoteDataSource
import com.vbogd.terminals.domain.repository.OrdersRepository
import com.vbogd.terminals.domain.repository.TerminalsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideTerminalRepository(
        terminalLocalDataSource: TerminalLocalDataSource,
        terminalRemoteDataSource: TerminalRemoteDataSource
    ): TerminalsRepository {
        return TerminalsRepositoryImpl(
            terminalLocalDataSource = terminalLocalDataSource,
            terminalRemoteDataSource = terminalRemoteDataSource
        )
    }

    @Provides
    @Singleton
    fun provideTerminalLocalDataSource(database: AppDatabase): TerminalLocalDataSource {
        return TerminalLocalDataSource.Base(database.terminalDao)
    }

    @Provides
    @Singleton
    fun provideOrderManager(
        orderLocalDataSource: OrderLocalDataSource,
        terminalsRepository: TerminalsRepository
    ): OrdersRepository {
        return OrderManager(
            orderLocalDataSource = orderLocalDataSource,
            terminalsRepository = terminalsRepository
        )
    }

    @Provides
    @Singleton
    fun provideOrderLocalDataSource(database: AppDatabase): OrderLocalDataSource {
        return OrderLocalDataSource.Base(database.orderDao)
    }

    @Provides
    @Singleton
    fun provideTerminalRemoteDataSource(retrofit: TerminalApi): TerminalRemoteDataSource {
        return TerminalRemoteDataSource.Base(retrofit)
    }
}