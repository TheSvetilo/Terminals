package com.vbogd.terminals.presentation.screen.direction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vbogd.terminals.domain.repository.OrdersRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject

class DirectionViewModelFactory @Inject constructor(
    private val orderRepository: OrdersRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DirectionViewModel::class.java)) {
            return DirectionViewModel(orderRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}