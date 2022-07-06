package com.vbogd.terminals.domain.repository

import com.vbogd.terminals.domain.model.Order

interface OrdersRepository {

    fun getOrderById(orderId: String): Order?
    fun updateOrder(order: Order)

}