package com.vbogd.terminals.data

import com.vbogd.terminals.data.orderRepository.local.OrderLocalDataSource
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.repository.OrdersRepository
import com.vbogd.terminals.domain.repository.TerminalsRepository
import javax.inject.Inject

class OrderManager @Inject constructor(
    private val orderLocalDataSource: OrderLocalDataSource,
    terminalsRepository: TerminalsRepository
) : OrdersRepository {

    override fun getOrderById(orderId: String): Order? {
        return Order(
            id = "1",
            terminalFrom = null,
            terminalTo = null
        )
    }

    override fun updateOrder(order: Order) {
        TODO("Not yet implemented")
    }

}
