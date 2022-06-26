package com.vbogd.terminals.presentation.screen.terminals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbogd.terminals.data.orderRepository.local.OrderService
import com.vbogd.terminals.data.terminalRepository.local.TerminalService
import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.Terminal


class TerminalsViewModel : ViewModel() {

    private val _currentOrderId = MutableLiveData<String>()
    val currentOrderId: LiveData<String> = _currentOrderId

    private val _terminalId = MutableLiveData<String>()
    val terminalId: LiveData<String> = _terminalId

    private val _orderDirection = MutableLiveData<Int>()
    val orderDirection: LiveData<Int> = _orderDirection

    private val _terminalsList = MutableLiveData<List<Terminal>>()
    val terminalList: LiveData<List<Terminal>> = _terminalsList

    private val terminalService = TerminalService()
    private val orderService = OrderService()

    init {
        _currentOrderId.value = orderService.order!!.id
        _terminalsList.value = terminalService.getTerminals()
        Log.d("TAG", _currentOrderId.value.toString())
    }

    fun getTerminalsByDirection(direction: Int) {
        _orderDirection.value = direction
    }

}