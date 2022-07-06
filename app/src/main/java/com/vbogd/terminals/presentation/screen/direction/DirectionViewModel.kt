package com.vbogd.terminals.presentation.screen.direction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.repository.OrdersRepository
import javax.inject.Inject

class DirectionViewModel @Inject constructor(
    private val repository: OrdersRepository
) : ViewModel() {

    private val _currentOrder = MutableLiveData<Order>()
    val currentOrder: LiveData<Order> = _currentOrder

    private val _isOrderCompleted = MutableLiveData<Boolean>()
    val isOrderCompleted: LiveData<Boolean> = _isOrderCompleted

    init {
        getLastOrder()
    }

    private fun getLastOrder() {
        _currentOrder.value = repository.getOrderById("1")
        _isOrderCompleted.value = false
    }

    fun setDirection(orderId: String, direction: Int, terminalId: String) {
//        _currentOrder.value = repository.fillOrder()
    }

}