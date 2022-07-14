package com.vbogd.terminals.domain.model

data class Terminal(
    val id: String,
    val name: String,
    val address: String,
    val workHours: String,
    var distance: Int,
    val latitude: Double,
    val longitude: Double,
    val imageAddress: String,
    val direction: Direction
)

enum class Direction {
    BOTH,
    FROM,
    TO,
    DEFAULT
}