package com.vbogd.terminals.data.terminalRepository.remote.dto

data class Phone(
    val comment: String,
    val number: String,
    val primary: Boolean,
    val type: String
)