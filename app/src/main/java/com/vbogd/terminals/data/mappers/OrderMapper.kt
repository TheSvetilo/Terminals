package com.vbogd.terminals.data.mappers

import com.vbogd.terminals.data.orderRepository.local.entity.OrderEntity
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.Terminal

fun convertOrderModelToEntity(
    order: Order
) = OrderEntity(
    id = order.id,
    terminalIdFrom = order.terminalFrom?.id.toString(),
    terminalIdTo = order.terminalTo?.id.toString()
)

fun convertOrderEntityToModel(
    orderEntity: OrderEntity,
    terminalFrom: Terminal?,
    terminalTo: Terminal?,
) = Order(
    id = orderEntity.id,
    terminalFrom = terminalFrom,
    terminalTo = terminalTo
)

