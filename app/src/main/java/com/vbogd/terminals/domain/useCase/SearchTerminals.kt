package com.vbogd.terminals.domain.useCase

import com.vbogd.terminals.domain.repository.TerminalsRepository
import javax.inject.Inject

class SearchTerminals @Inject constructor(
    private val terminalsRepository: TerminalsRepository
) {

    fun searchTerminal(search: String) {
        terminalsRepository.searchTerminal(search)

    }
}