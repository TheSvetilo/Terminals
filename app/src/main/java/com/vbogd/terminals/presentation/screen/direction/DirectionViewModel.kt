package com.vbogd.terminals.presentation.screen.direction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbogd.terminals.data.orderRepository.local.OrderService
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.Terminal

class DirectionViewModel : ViewModel() {

    private val repository = OrderService()

    private val _currentOrder = MutableLiveData<Order>()
    val currentOrder: LiveData<Order> = _currentOrder

    private val _isOrderCompleted = MutableLiveData<Boolean>()
    val isOrderCompleted: LiveData<Boolean> = _isOrderCompleted

    init {
        getLastOrder()
    }

    private fun getLastOrder() {
        _currentOrder.value = repository.getOrderById()
        _isOrderCompleted.value = false
    }

    fun setDirection(orderId: String, direction: Int, terminalId: String) {
        _currentOrder.value = repository.fillOrder()
    }

    private fun findTerminalById(terminalId: String): Terminal? {
        return Terminal(
            id = "4",
            name = "Санкт-Петербург Удельная",
            address = "194292, Санкт-Петербург г, 4-й Верхний пер, дом № 12, Литера Б",
            workHours = "пн: 08:00-00:00; вт-пт: круглосуточно; сб: 00:00-20:00; вс: 09:00-20:00",
            distance = 2.2,
            imageAddress = "",
            direction = false
        )
    }


}