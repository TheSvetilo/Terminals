package com.vbogd.terminals.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vbogd.terminals.data.orderRepository.local.dao.OrderDao
import com.vbogd.terminals.data.orderRepository.local.entity.OrderEntity
import com.vbogd.terminals.data.terminalRepository.local.dao.TerminalDao
import com.vbogd.terminals.data.terminalRepository.local.entity.TerminalEntity

@Database(
    entities = [
        OrderEntity::class,
        TerminalEntity::class
    ],
    version = 7,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val orderDao: OrderDao
    abstract val terminalDao: TerminalDao
}