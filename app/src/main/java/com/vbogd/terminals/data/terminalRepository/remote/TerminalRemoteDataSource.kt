package com.vbogd.terminals.data.terminalRepository.remote

import com.vbogd.terminals.data.terminalRepository.remote.dto.TerminalsDto
import io.reactivex.Single
import javax.inject.Inject

interface TerminalRemoteDataSource {

    fun getTerminals(): Single<TerminalsDto>

    class Base @Inject constructor(
        private val terminalApi: TerminalApi
    ) : TerminalRemoteDataSource {

        override fun getTerminals(): Single<TerminalsDto> {
            return terminalApi.getTerminals()
        }

    }
}