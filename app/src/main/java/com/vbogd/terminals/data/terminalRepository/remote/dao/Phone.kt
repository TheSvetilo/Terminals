package com.vbogd.terminals.data.terminalRepository.remote.dao

data class Phone(
    val comment: String,
    val number: String,
    val primary: Boolean,
    val type: String
)