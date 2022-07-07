package com.vbogd.terminals.presentation.screen.terminals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.domain.model.Terminal
import com.vbogd.terminals.domain.repository.TerminalsRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class TerminalsViewModel @Inject constructor(
    private val terminalsRepository: TerminalsRepository
) : ViewModel() {

    private val _currentOrderId = MutableLiveData<String>()
    val currentOrderId: LiveData<String> = _currentOrderId

    private val _terminalId = MutableLiveData<String>()
    val terminalId: LiveData<String> = _terminalId

    private val _orderDirection = MutableLiveData<Int>()
    val orderDirection: LiveData<Int> = _orderDirection

    private val _terminalsList = MutableLiveData<List<Terminal>>()
    val terminalList: LiveData<List<Terminal>> = _terminalsList

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    fun getTerminalsByDirection(direction: Direction) {

        _dataLoading.value = true

        terminalsRepository.getTerminalsByDirection(direction)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _terminalsList.value = it
                _dataLoading.value = false
//                _orderDirection.value = if (direction == Direction.FROM) 0 else 1
            }, {

            })

    }

}