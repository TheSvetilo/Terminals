package com.vbogd.terminals.data.terminalRepository

import android.util.Log
import com.vbogd.terminals.data.terminalRepository.local.TerminalLocalDataSource
import com.vbogd.terminals.data.terminalRepository.remote.TerminalRemoteDataSource
import com.vbogd.terminals.data.terminalRepository.remote.dto.TerminalsDto
import com.vbogd.terminals.data.terminalRepository.remote.dto.toDomain
import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.domain.model.Terminal
import com.vbogd.terminals.domain.repository.TerminalsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TerminalsRepositoryImpl @Inject constructor(
    private val terminalLocalDataSource: TerminalLocalDataSource,
    private val terminalRemoteDataSource: TerminalRemoteDataSource
) : TerminalsRepository {

    override fun getTerminals(): Single<List<Terminal>> {

        return terminalRemoteDataSource.getTerminals()
            .subscribeOn(Schedulers.io())
            .flatMap {
                return@flatMap Single.just(fetchTerminalsFromDtoList(it))
            }

    }

    override fun getTerminalsByDirection(direction: Direction): Single<List<Terminal>> {
        return terminalRemoteDataSource.getTerminals()
            .subscribeOn(Schedulers.io())
            .flatMap { terminalsDto ->
                val terminals = fetchTerminalsFromDtoList(terminalsDto)
                val result = when (direction) {
                    Direction.FROM -> {
                        terminals.filter { it.direction == Direction.FROM || it.direction == Direction.BOTH }
                    }
                    Direction.TO -> {
                        terminals.filter { it.direction == Direction.TO || it.direction == Direction.BOTH }
                    }
                    else -> {
                        terminals
                    }
                }
                return@flatMap Single.just(result)
            }
    }

    private fun fetchTerminalsFromDtoList(terminalsDto: TerminalsDto): List<Terminal> {
        val terminals = mutableListOf<Terminal>()
        for (city in terminalsDto.city) {
            for (terminal in city.terminals.terminal) {
                terminals.add(terminal.toDomain())
            }
        }
//        for (ter in terminals) {
//            Log.d("TAG", "Name: ${ter.name}, direction: ${ter.direction}")
//        }
        return terminals
    }

    override fun getTerminalById(id: String): Single<Terminal?> {
        TODO("Not yet implemented")
    }

    override fun searchTerminal(search: String): Observable<List<Terminal>> {
        TODO("Not yet implemented")
    }

    override fun saveTerminal(terminal: Terminal): Completable {
        TODO("Not yet implemented")
    }

    override fun saveAll(terminals: List<Terminal>): Completable {
        TODO("Not yet implemented")
    }
}