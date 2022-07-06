package com.vbogd.terminals.data.mappers

import com.vbogd.terminals.data.orderRepository.local.entity.OrderEntity
import com.vbogd.terminals.data.terminalRepository.local.entity.TerminalEntity
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.Terminal

fun convertOrderModelToEntity(order: Order): OrderEntity {
    return OrderEntity(
        id = order.id,
        terminalIdFrom = order.terminalFrom?.id.toString(),
        terminalIdTo = order.terminalTo?.id.toString()
    )
}

fun convertOrderEntityToModel(orderEntity: OrderEntity): Order {
    return Order(
        id = orderEntity.id,
        terminalFrom = null,
        terminalTo = null
    )
}