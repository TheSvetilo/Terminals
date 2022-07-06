package com.vbogd.terminals.domain.useCase

import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.domain.model.Terminal
import com.vbogd.terminals.domain.repository.TerminalsRepository
import javax.inject.Inject

class FilterTerminalUseCase @Inject constructor(
    private val terminalsRepository: TerminalsRepository
) {

//    fun getTerminalsByDirection(direction: Direction): List<Terminal> {
//        return when (direction) {
//            Direction.FROM -> terminalsRepository.getTerminalsByDirection()
//            Direction.TO -> terminalsRepository.getTerminalsByDirection()
//        }
//    }
}