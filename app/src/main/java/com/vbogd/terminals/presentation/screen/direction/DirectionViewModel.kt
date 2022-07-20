package com.vbogd.terminals.presentation.screen.direction

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.OrderStatus
import com.vbogd.terminals.domain.repository.OrdersRepository
import com.vbogd.terminals.domain.repository.TerminalsRepository
import com.vbogd.terminals.utils.Constants
import io.reactivex.Single
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

    fun getLastOrder() {
        _dataLoading.value = true
        orderRepository.getOrderById(Constants.ORDER_SAMPLE_ID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ order ->
                _currentOrder.value = order
                _dataLoading.value = false
                updateButtonState()
            }, {
                _dataLoading.value = false
            })
    }

    fun setDirection(direction: Int, terminalId: String) {

        _dataLoading.value = true
        Single.zip(
            orderRepository.getOrderById(Constants.ORDER_SAMPLE_ID),
            terminalsRepository.getTerminalById(terminalId),
        ) { order, terminal -> Pair(order, terminal) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _currentOrder.value = it.first
                when (direction) {
                    0 -> _currentOrder.value =
                        _currentOrder.value?.copy(terminalFrom = it.second.get())
                    1 -> _currentOrder.value =
                        _currentOrder.value?.copy(terminalTo = it.second.get())
                }
                _dataLoading.value = false
                updateOrder()
                updateButtonState()
            }, {
                Log.d("TAG", "Error: ${it.message}")
            })
    }

    private fun updateOrder() {
        _currentOrder.value?.let {
            orderRepository.updateOrder(it)
                .subscribeOn(Schedulers.io())
                .subscribe()
        }
    }

    fun saveOrder() {
        _currentOrder.value = _currentOrder.value?.copy(status = OrderStatus.READY_TO_GO)
        updateOrder()
    }

    fun clearOrderData() {
        _currentOrder.value =
            _currentOrder.value?.copy(
                terminalFrom = null,
                terminalTo = null,
                status = OrderStatus.DRAFT
            )
        updateOrder()
        updateButtonState()
    }

    private fun updateButtonState() {
        _isOrderCompleted.value =
            _currentOrder.value?.terminalFrom != null && _currentOrder.value?.terminalTo != null
    }

}