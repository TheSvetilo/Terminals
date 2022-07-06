package com.vbogd.terminals.data.terminalRepository.local

import com.vbogd.terminals.data.terminalRepository.local.dao.TerminalDao
import javax.inject.Inject

interface TerminalLocalDataSource {



    class Base @Inject constructor(
        private val terminalDao: TerminalDao
    ) : TerminalLocalDataSource {}
}