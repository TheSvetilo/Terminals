package com.vbogd.terminals.domain.repository

import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.domain.model.Terminal
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface TerminalsRepository {

    fun getTerminalsByDirection(direction: Direction): Single<List<Terminal>>
    fun getTerminalById(id: String): Single<Terminal?>
    fun searchTerminal(search: String): Observable<List<Terminal>>
    fun saveTerminal(terminal: Terminal): Completable
    fun saveAll(terminals: List<Terminal>): Completable

    // temp
    fun getTerminals(): Single<List<Terminal>>

}