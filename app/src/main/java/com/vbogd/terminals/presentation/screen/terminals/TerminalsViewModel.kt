package com.vbogd.terminals.presentation.screen.terminals

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbogd.terminals.domain.model.Terminal
import com.vbogd.terminals.domain.repository.TerminalsRepository
import javax.inject.Inject


class TerminalsViewModel @Inject constructor(
    terminalsRepository: TerminalsRepository
) : ViewModel() {

    private val _currentOrderId = MutableLiveData<String>()
    val currentOrderId: LiveData<String> = _currentOrderId

    private val _terminalId = MutableLiveData<String>()
    val terminalId: LiveData<String> = _terminalId

    private val _orderDirection = MutableLiveData<Int>()
    val orderDirection: LiveData<Int> = _orderDirection

    private val _terminalsList = MutableLiveData<List<Terminal>>()
    val terminalList: LiveData<List<Terminal>> = _terminalsList

    init {
        _terminalsList.value = terminalsRepository.getTerminals()
        Log.d("TAG", _terminalsList.value.toString())
//        _currentOrderId.value = terminalRepository.getTerminalById("1")
    }

    fun getTerminalsByDirection(direction: Int) {
        _orderDirection.value = direction
//        terminalRepository.getTerminals()
    }

}