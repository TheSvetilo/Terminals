package com.vbogd.terminals.data.mappers

import com.vbogd.terminals.data.terminalRepository.local.entity.TerminalEntity
import com.vbogd.terminals.domain.model.Terminal

fun convertTerminalEntityToModel(terminalEntity: TerminalEntity): Terminal {
    return Terminal(
        id = terminalEntity.id,
        name = terminalEntity.name,
        address = terminalEntity.address,
        workHours = terminalEntity.workHours,
        distance = terminalEntity.distance,
        imageAddress = terminalEntity.imageAddress,
        direction = terminalEntity.direction
    )
}

fun convertTerminalModelToEntity(terminal: Terminal): TerminalEntity {
    return TerminalEntity(
        id = terminal.id,
        name = terminal.name,
        address = terminal.address,
        workHours = terminal.workHours,
        distance = terminal.distance,
        imageAddress = terminal.imageAddress,
        direction = terminal.direction
    )
}

fun convertTerminalDtoToModel(terminalDto: com.vbogd.terminals.data.terminalRepository.remote.dto.Terminal): Terminal {
    return Terminal(
        id = terminalDto.id,
        name = terminalDto.name,
        address = terminalDto.address,
        workHours = terminalDto.calcSchedule.arrival,
        distance = terminalDto.maxHeight,
        imageAddress = "https://assets.dellin.ru/assets/yamaps_public/2021/10/18/20633770/original/658.png#da2110ff08a529eed7ff06664a1c44f5f7d6b5c78ae41255057957194fe1f647",
        direction = !(terminalDto.default && terminalDto.giveoutCargo)
    )
}