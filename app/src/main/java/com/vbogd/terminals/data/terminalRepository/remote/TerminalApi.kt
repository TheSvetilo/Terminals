package com.vbogd.terminals.data.terminalRepository.remote

import com.vbogd.terminals.data.terminalRepository.remote.dto.TerminalsDto
import io.reactivex.Single
import retrofit2.http.GET

interface TerminalApi {

    @GET("static/catalog/terminals_v3.json")
    fun getTerminals(): Single<TerminalsDto>

}