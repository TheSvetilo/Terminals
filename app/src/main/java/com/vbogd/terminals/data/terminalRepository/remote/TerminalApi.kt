package com.vbogd.terminals.data.terminalRepository.remote

import com.vbogd.terminals.data.terminalRepository.remote.dao.Terminals
import retrofit2.http.GET

interface TerminalApi {

    @GET("static/catalog/terminals_v3.json")
    fun getTerminals(): Terminals

}