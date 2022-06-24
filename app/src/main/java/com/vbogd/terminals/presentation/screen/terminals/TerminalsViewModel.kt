package com.vbogd.terminals.presentation.screen.terminals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbogd.terminals.data.terminalRepository.local.TerminalService
import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.Terminal


class TerminalsViewModel(
    currentOrderId: String,
    orderDirection: Int
) : ViewModel() {

    private val _currentOrder = MutableLiveData<Order>()
    val currentOrder: LiveData<Order> = _currentOrder

    private val _terminalId = MutableLiveData<String>()
    val terminalId: LiveData<String> = _terminalId

    private val _orderDirection = MutableLiveData<Int>()
    val orderDirection: LiveData<Int> = _orderDirection

    private val _terminalsList = MutableLiveData<List<Terminal>>()
    val terminalList: LiveData<List<Terminal>> = _terminalsList

    private val repository = TerminalService()

    init {
        _terminalsList.value = repository.getTerminals()
//        _currentOrder.value = currentOrderId
        _orderDirection.value = orderDirection
        Log.d("TAG", _orderDirection.value.toString())
    }

    fun setTerminal(terminalId: String, tabId: Int) {
        _terminalId.value = terminalId
        _orderDirection.value = tabId
    }

    fun getTerminalsByDirection(direction: Direction) {

    }

}