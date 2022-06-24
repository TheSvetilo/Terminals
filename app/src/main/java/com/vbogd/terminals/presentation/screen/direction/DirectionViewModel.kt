package com.vbogd.terminals.presentation.screen.direction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbogd.terminals.data.orderRepository.local.OrderService
import com.vbogd.terminals.domain.model.Order

class DirectionViewModel : ViewModel() {

    private val _currentOrder = MutableLiveData<Order>()
    val currentOrder: LiveData<Order> = _currentOrder

    private val _isOrderFilled = MutableLiveData<Boolean>()
    val isOrderFilled: LiveData<Boolean> = _isOrderFilled

    private val repository = OrderService()

    init {
        _currentOrder.value = repository.getOrder()
    }

    private fun getLastOrder() {
        _currentOrder.value = repository.getOrder()
        _isOrderFilled.value = false
    }

    private fun saveTerminalInOrder() {

    }

    fun saveCurrentOrder() {

    }

//    private fun fillOrder() {
//        _currentOrder.value = repository.fillOrder()
//        _isOrderFilled.value = true
//    }


}