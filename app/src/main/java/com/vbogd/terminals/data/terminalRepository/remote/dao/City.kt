package com.vbogd.terminals.data.terminalRepository.remote.dao

data class City(
    val cityID: Int,
    val code: String,
    val day2dayRequest: String,
    val day2daySFRequest: String,
    val freeStorageDays: String,
    val id: String,
    val latitude: String,
    val longitude: String,
    val name: String,
    val preorderRequest: String,
    val requestEndTime: String,
    val sfrequestEndTime: String,
    val terminals: Terminals,
    val timeshift: String,
    val url: String
)