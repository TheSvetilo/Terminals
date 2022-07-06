package com.vbogd.terminals.data.terminalRepository

import android.accounts.NetworkErrorException
import android.util.Log
import com.vbogd.terminals.data.terminalRepository.local.TerminalLocalDataSource
import com.vbogd.terminals.data.terminalRepository.remote.TerminalRemoteDataSource
import com.vbogd.terminals.data.terminalRepository.remote.dto.toDomain
import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.domain.repository.TerminalsRepository
import com.vbogd.terminals.domain.model.Terminal
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TerminalsRepositoryImpl @Inject constructor(
    private val terminalLocalDataSource: TerminalLocalDataSource,
    private val terminalRemoteDataSource: TerminalRemoteDataSource
) : TerminalsRepository {


    override fun getTerminals(): List<Terminal> {

        val terminals = mutableListOf<Terminal>()

        val sub = terminalRemoteDataSource.getTerminals()
            .subscribeOn(Schedulers.io())
            .subscribe({ terminalsDto ->
                terminalsDto?.let {
                    for (city in terminalsDto.city) {
                        for (terminal in city.terminals.terminal) {
                            terminals.add(terminal.toDomain())
                            Log.d("TAG", terminal.name)
                        }
                    }
                }
            }, {
                throw NetworkErrorException("Network issues occur.")
            })

        return terminals
    }

    override fun getTerminalsByDirection(direction: Direction): List<Terminal> {
        TODO("Not yet implemented")
    }

    override fun getTerminalById(id: String): Terminal? {
        TODO("Not yet implemented")
    }

    override fun searchTerminal(search: String): List<Terminal> {
        TODO("Not yet implemented")
    }

    override fun saveTerminal(terminal: Terminal) {
        TODO("Not yet implemented")
    }

    override fun saveAll(terminals: List<Terminal>) {
        TODO("Not yet implemented")
    }
}