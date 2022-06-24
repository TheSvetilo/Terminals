package com.vbogd.terminals.domain.model

data class Order(
    val id: String,
    val terminalFrom: Terminal? = null,
    val terminalTo: Terminal? = null
)
