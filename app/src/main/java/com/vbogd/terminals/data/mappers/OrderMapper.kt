package com.vbogd.terminals.data.mappers

import com.vbogd.terminals.data.orderRepository.local.entity.OrderEntity
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.OrderStatus
import com.vbogd.terminals.domain.model.Terminal

fun convertOrderModelToEntity(
    order: Order
) = OrderEntity(
    id = order.id,
    terminalIdFrom = order.terminalFrom?.id.toString(),
    terminalIdTo = order.terminalTo?.id.toString(),
    status = convertOrderStatus(order.status)
)

fun convertOrderEntityToModel(
    orderEntity: OrderEntity,
    terminalFrom: Terminal?,
    terminalTo: Terminal?,
) = Order(
    id = orderEntity.id,
    terminalFrom = terminalFrom,
    terminalTo = terminalTo,
    status = convertOrderStatus(orderEntity.status)
)

fun convertOrderStatus(
    orderStatus: OrderStatus
) = when (orderStatus) {
    OrderStatus.DRAFT -> 0
    OrderStatus.READY_TO_GO -> 1
    OrderStatus.PROGRESS -> 2
    OrderStatus.CLOSED -> 3
    OrderStatus.UNDEFINED -> -1
}

fun convertOrderStatus(
    orderStatus: Int
) = when (orderStatus) {
    0 -> OrderStatus.DRAFT
    1 -> OrderStatus.READY_TO_GO
    2 -> OrderStatus.PROGRESS
    3 -> OrderStatus.CLOSED
    else -> OrderStatus.UNDEFINED
}