package com.vbogd.terminals.data.orderRepository.local

import com.vbogd.terminals.data.orderRepository.OrderRepository
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.Terminal

class OrderService : OrderRepository {

    var orderList = emptyList<Order>()

    init {
        getOrder()
    }

    override fun getOrder(): Order {
        return Order(
            id = "1",
            terminalFrom = null,
            terminalTo = null
        )
    }

    fun fillOrder(): Order {
        return Order(
            id = "2",
            terminalFrom = Terminal(
                id = "1",
                name = "Санкт-Петербург Парнас",
                address = "194292, Санкт-Петербург г, 1-й Верхний пер, дом № 12, Литера Б",
                workHours = "пн: 08:00-00:00; вт-пт: круглосуточно; сб: 00:00-20:00; вс: 09:00-20:00",
                distance = 2.2,
                imageAddress = "",
                direction = false
            ),
            terminalTo = Terminal(
                id = "2",
                name = "Санкт-Петербург Парнас",
                address = "194292, Санкт-Петербург г, 1-й Верхний пер, дом № 12, Литера Б",
                workHours = "пн: 08:00-00:00; вт-пт: круглосуточно; сб: 00:00-20:00; вс: 09:00-20:00",
                distance = 2.2,
                imageAddress = "",
                direction = true
            )
        )
    }
}