package com.vbogd.terminals.data.terminalRepository.local

import com.vbogd.terminals.data.terminalRepository.local.dao.TerminalDao
import com.vbogd.terminals.data.terminalRepository.local.entity.TerminalEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface TerminalLocalDataSource {

    fun getTerminalById(terminalId: String): Single<TerminalEntity?>
    fun saveTerminals(terminals: List<TerminalEntity>): Completable
    fun saveTerminal(terminalEntity: TerminalEntity): Completable
    fun getTerminals(): Single<List<TerminalEntity>>

    class Base @Inject constructor(
        private val terminalDao: TerminalDao
    ) : TerminalLocalDataSource {

        override fun getTerminalById(terminalId: String): Single<TerminalEntity?> {
            return terminalDao.getTerminalById(terminalId)
        }

        override fun saveTerminals(terminals: List<TerminalEntity>): Completable {
            return terminalDao.saveAll(terminals)
        }

        override fun saveTerminal(terminalEntity: TerminalEntity): Completable {
            return terminalDao.saveTerminal(terminalEntity)
        }

        override fun getTerminals(): Single<List<TerminalEntity>> {
            return terminalDao.getTerminals()
        }

    }
}