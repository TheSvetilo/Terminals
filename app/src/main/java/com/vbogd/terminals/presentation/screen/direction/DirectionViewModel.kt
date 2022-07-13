package com.vbogd.terminals.presentation.screen.direction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.repository.OrdersRepository
import com.vbogd.terminals.domain.repository.TerminalsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DirectionViewModel @Inject constructor(
    private val orderRepository: OrdersRepository,
    private val terminalsRepository: TerminalsRepository
) : ViewModel() {

    private val _currentOrder = MutableLiveData<Order>()
    val currentOrder: LiveData<Order> = _currentOrder

    private val _isOrderCompleted = MutableLiveData<Boolean>()
    val isOrderCompleted: LiveData<Boolean> = _isOrderCompleted

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    init {
        if (_currentOrder.value == null) {
            getLastOrder()
        }
    }

    private fun getLastOrder() {
        _dataLoading.value = true
        orderRepository.getOrderById("55")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ order ->
                Log.d("TAG", "OrderViewModel: order from DATABASE is here: $order")
                _currentOrder.value = order
                _dataLoading.value = false
                updateButtonState()
            }, {
                Log.d("TAG", "Order from DATABASE doesn't exist: ${it.message}")
                _dataLoading.value = false
            })
    }

    fun setDirection(direction: Int, terminalId: String) {

        Log.d("TAG", "Set direction is: $direction")
        _dataLoading.value = true
        terminalsRepository.getTerminalById(terminalId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TAG", "Current BEFORE order is: ${_currentOrder.value}")
                when (direction) {
                    0 -> _currentOrder.value = _currentOrder.value?.copy(terminalFrom = it.get())
                    1 -> _currentOrder.value = _currentOrder.value?.copy(terminalTo = it.get())
                }
                updateButtonState()
                _dataLoading.value = false
                Log.d("TAG", "Current AFTER order is: ${_currentOrder.value}")
                saveOrder()
            }, {
                Log.d("TAG", "Order can't be founded by some reasons.")
            })

    }

    fun saveOrder() {
        _currentOrder.value?.let {
            orderRepository.updateOrder(it)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d("TAG", "Order has been successful saved.")
                }, {
                    Log.d("TAG", "Order can't be successful saved for some reasons.")
                })
        }
    }

    fun clearOrderData() {
        Log.d("TAG", "Start clearing data. Order before: ${_currentOrder.value}")
        _currentOrder.value = _currentOrder.value?.copy(terminalFrom = null, terminalTo = null)
        Log.d("TAG", "Start clearing data. Order process: ${_currentOrder.value}")
        orderRepository.updateOrder(_currentOrder.value!!)
            .subscribeOn(Schedulers.io())
            .subscribe({
                Log.d("TAG", "Data cleared.")
            }, {

            })
        updateButtonState()
    }

    private fun updateButtonState() {
        _isOrderCompleted.value =
            _currentOrder.value?.terminalFrom != null && _currentOrder.value?.terminalTo != null
    }

}