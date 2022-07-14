package com.vbogd.terminals.data.terminalRepository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "terminals")
data class TerminalEntity(
    @PrimaryKey val id: String,
    val name: String,
    val address: String,
    val workHours: String,
    val distance: Double,
    val latitude: Double,
    val longitude: Double,
    val imageAddress: String,
    val direction: String
)