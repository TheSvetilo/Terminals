package com.vbogd.terminals.data.mappers

import com.vbogd.terminals.data.terminalRepository.local.entity.TerminalEntity
import com.vbogd.terminals.domain.model.Terminal

fun convertTerminalModelToEntity(terminal: Terminal): TerminalEntity {
    return TerminalEntity(
        id = terminal.id,
        name = terminal.name,
        address = terminal.address,
        workHours = terminal.workHours,
        distance = 0.0,
        imageAddress = terminal.imageAddress,
        direction = terminal.direction.toString(),
        latitude = terminal.latitude,
        longitude = terminal.longitude
    )
}
