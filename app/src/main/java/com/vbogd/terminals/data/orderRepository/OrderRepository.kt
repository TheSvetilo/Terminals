package com.vbogd.terminals.data.orderRepository

import com.vbogd.terminals.domain.model.Order

interface OrderRepository {

    fun getOrders(): List<Order>

}