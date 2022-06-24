package com.vbogd.terminals.domain

import com.vbogd.terminals.domain.model.Terminal

interface TerminalRepository {

    fun getTerminals(): List<Terminal>
    fun getTerminalById(id: Int): Terminal?

}