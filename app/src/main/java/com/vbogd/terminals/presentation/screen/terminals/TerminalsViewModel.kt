package com.vbogd.terminals.presentation.screen.terminals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.domain.model.Terminal
import com.vbogd.terminals.domain.repository.TerminalsRepository
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

    private val _filter = MutableLiveData<TerminalFilter>()
    val filter: LiveData<TerminalFilter> = _filter

    init {
        _filter.value = TerminalFilter.DEFAULT
    }

    fun getTerminalsByDirection(direction: Direction) {

        _dataLoading.value = true
        _filter.value = TerminalFilter.DEFAULT

        terminalsRepository.getTerminalsByDirection(direction)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { terminalList ->
                when (direction) {
                    Direction.FROM -> {
                        terminalList.filter { it.direction == Direction.FROM || it.direction == Direction.BOTH }
                    }
                    Direction.TO -> {
                        terminalList.filter { it.direction == Direction.TO || it.direction == Direction.BOTH }
                    }
                    else -> {
                        terminalList
                    }
                }
            }
            .subscribe({
                _terminalsList.value = it
                _dataLoading.value = false
            }, {

            })

    }

    fun applyTerminalFilter(filter: TerminalFilter) {

        _filter.value = filter

        _terminalsList.value = _terminalsList.value?.sortedBy { it.name }
    }

}

enum class TerminalFilter {
    DEFAULT,
    NAME,
    DISTANCE
}