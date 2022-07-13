package com.vbogd.terminals.data.terminalRepository

import android.util.Log
import com.vbogd.terminals.data.mappers.convertTerminalModelToEntity
import com.vbogd.terminals.data.terminalRepository.local.TerminalLocalDataSource
import com.vbogd.terminals.data.terminalRepository.local.entity.TerminalEntity
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
            .map {
                fetchTerminalsFromDtoList(it)
            }
    }

    override fun getTerminalsByDirection(direction: Direction): Single<List<Terminal>> {
        return terminalRemoteDataSource.getTerminals()
            .subscribeOn(Schedulers.io())
//            .flatMap { terminalsDto ->
//                val terminals = fetchTerminalsFromDtoList(terminalsDto)
//                val result = when (direction) {
//                    Direction.FROM -> {
//                        terminals.filter { it.direction == Direction.FROM || it.direction == Direction.BOTH }
//                    }
//                    Direction.TO -> {
//                        terminals.filter { it.direction == Direction.TO || it.direction == Direction.BOTH }
//                    }
//                    else -> {
//                        terminals
//                    }
//                }
//                return@flatMap Single.just(result)
//            }
            .map {
                fetchTerminalsFromDtoList(it)
            }
    }

    private fun fetchTerminalsFromDtoList(terminalsDto: TerminalsDto): List<Terminal> {
        val terminals = mutableListOf<Terminal>()
        for (city in terminalsDto.city) {
            for (terminal in city.terminals.terminal) {
                terminals.add(terminal.toDomain())
            }
        }

        return terminals
    }

    private fun fetchTerminalById(terminalsDto: TerminalsDto, terminalId: String): Terminal? {

        var result: Terminal? = null
        for (terminal in fetchTerminalsFromDtoList(terminalsDto)) {
            if (terminal.id == terminalId) {
                result = terminal
            }
        }
        Log.d("TAG", "TerminalRepository: fetchTerminalById: $result")
        return result

    }

    override fun getTerminalById(terminalId: String): Single<Terminal?> {
        return terminalRemoteDataSource.getTerminals()
            .subscribeOn(Schedulers.io())
            .map { fetchTerminalById(it, terminalId) }
    }

    override fun searchTerminal(search: String): Observable<List<Terminal>> {
        TODO("Not yet implemented")
    }

    override fun saveTerminal(terminal: Terminal): Completable {
        TODO("Not yet implemented")
    }

    override fun saveAll(terminals: List<Terminal>): Completable {

        val terminalEntityList = mutableListOf<TerminalEntity>()
        for (terminal in terminals) {
            terminalEntityList.add(convertTerminalModelToEntity(terminal))
        }
        return terminalLocalDataSource.saveTerminals(terminalEntityList)
    }
}