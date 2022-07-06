package com.vbogd.terminals.di.module

import com.vbogd.terminals.domain.repository.OrdersRepository
import com.vbogd.terminals.domain.repository.TerminalsRepository
import com.vbogd.terminals.presentation.screen.direction.DirectionViewModel
import com.vbogd.terminals.presentation.screen.direction.DirectionViewModelFactory
import com.vbogd.terminals.presentation.screen.terminals.TerminalsViewModel
import com.vbogd.terminals.presentation.screen.terminals.TerminalsViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun bindDirectionViewModel(
        orderManager: OrdersRepository
    ): DirectionViewModelFactory {
        return DirectionViewModelFactory(orderManager)
    }

    @Provides
    fun bindTerminalsViewModelFactory(
        terminalsRepository: TerminalsRepository
    ): TerminalsViewModelFactory {
        return TerminalsViewModelFactory(terminalsRepository)
    }
}