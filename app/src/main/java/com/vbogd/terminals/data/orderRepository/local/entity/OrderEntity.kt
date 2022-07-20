package com.vbogd.terminals.data.orderRepository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val id: String,
    val terminalIdFrom: String?,
    val terminalIdTo: String?
)
