package com.vbogd.terminals.domain.model

data class Terminal(
    val id: Int,
    val name: String,
    val address: String,
    val workHours: String,
    val distance: Double,
    val imageAddress: String
)
