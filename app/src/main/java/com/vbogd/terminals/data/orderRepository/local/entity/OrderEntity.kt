package com.vbogd.terminals.data.orderRepository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vbogd.terminals.utils.Constants

@Entity(tableName = Constants.ORDER_TABLE_NAME)
data class OrderEntity(
    @PrimaryKey val id: String,
    val terminalIdFrom: String?,
    val terminalIdTo: String?,
    val status: Int
)
