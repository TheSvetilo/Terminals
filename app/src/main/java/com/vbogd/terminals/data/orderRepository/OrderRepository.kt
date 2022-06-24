package com.vbogd.terminals.data.orderRepository

import com.vbogd.terminals.domain.model.Order

interface OrderRepository {

    fun getOrder(): Order

}