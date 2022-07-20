package com.vbogd.terminals.domain.repository

import com.vbogd.terminals.domain.model.Terminal
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

interface TerminalsRepository {

    fun getTerminals(): Single<List<Terminal>>
    fun getTerminalById(terminalId: String): Single<Optional<Terminal>>
    fun searchTerminal(search: String): Observable<List<Terminal>>
    fun saveTerminal(terminal: Terminal): Completable
    fun saveAll(terminals: List<Terminal>): Completable

}