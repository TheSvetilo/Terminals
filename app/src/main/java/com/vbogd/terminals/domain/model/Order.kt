package com.vbogd.terminals.domain.model

data class Order(
    val id: String,
    val terminalFrom: Terminal? = null,
    val terminalTo: Terminal? = null,
    val status: OrderStatus
)

enum class OrderStatus {
    DRAFT,
    READY_TO_GO,
    PROGRESS,
    CLOSED,
    UNDEFINED
}