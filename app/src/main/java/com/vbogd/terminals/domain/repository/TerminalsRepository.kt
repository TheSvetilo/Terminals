package com.vbogd.terminals.domain.repository

import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.domain.model.Terminal

interface TerminalsRepository {

    fun getTerminalsByDirection(direction: Direction): List<Terminal>
    fun getTerminalById(id: String): Terminal?
    fun searchTerminal(search: String): List<Terminal>
    fun saveTerminal(terminal: Terminal)
    fun saveAll(terminals: List<Terminal>)

    // temp
    fun getTerminals(): List<Terminal>

}