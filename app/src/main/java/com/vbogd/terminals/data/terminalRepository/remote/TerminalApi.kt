package com.vbogd.terminals.data.terminalRepository.remote

import com.vbogd.terminals.data.terminalRepository.remote.dto.TerminalsDto
import com.vbogd.terminals.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET

interface TerminalApi {

    @GET(Constants.TERMINALS_GET_TERMINALS_URL)
    fun getTerminals(): Single<TerminalsDto>

}