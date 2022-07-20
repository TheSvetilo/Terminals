package com.vbogd.terminals.data.terminalRepository

import com.vbogd.terminals.data.mappers.convertTerminalModelToEntity
import com.vbogd.terminals.data.terminalRepository.local.TerminalLocalDataSource
import com.vbogd.terminals.data.terminalRepository.local.entity.TerminalEntity
import com.vbogd.terminals.data.terminalRepository.remote.TerminalRemoteDataSource
import com.vbogd.terminals.data.terminalRepository.remote.dto.TerminalsDto
import com.vbogd.terminals.data.terminalRepository.remote.dto.toDomain
import com.vbogd.terminals.domain.model.Terminal
import com.vbogd.terminals.domain.repository.TerminalsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*
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
        return result

    }

    override fun getTerminalById(terminalId: String): Single<Optional<Terminal>> {
        return terminalRemoteDataSource.getTerminals()
            .subscribeOn(Schedulers.io())
            .map { Optional.ofNullable(fetchTerminalById(it, terminalId)) }
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