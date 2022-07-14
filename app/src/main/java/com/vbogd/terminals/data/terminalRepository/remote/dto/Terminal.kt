package com.vbogd.terminals.data.terminalRepository.remote.dto

import com.vbogd.terminals.domain.model.Direction
import java.lang.Exception

data class Terminal(
    val address: String,
    val addressCode: AddressCode,
    val calcSchedule: CalcSchedule,
    val default: Boolean,
    val fullAddress: String,
    val giveoutCargo: Boolean,
    val id: String,
    val isOffice: Boolean,
    val isPVZ: Boolean,
    val latitude: String,
    val longitude: String,
    val mail: String,
    val mainPhone: String,
    val maps: Maps,
    val maxHeight: Double,
    val maxLength: Double,
    val maxShippingVolume: Double,
    val maxShippingWeight: Double,
    val maxVolume: Double,
    val maxWeight: Double,
    val maxWidth: Double,
    val name: String,
    val phones: List<Phone>,
    val receiveCargo: Boolean,
    val storage: Boolean,
    val worktables: Worktables
)

fun Terminal.toDomain(): com.vbogd.terminals.domain.model.Terminal {

    val direction = if (default && giveoutCargo && receiveCargo) Direction.BOTH
    else if (default && giveoutCargo) Direction.TO
    else if (receiveCargo) Direction.FROM
    else Direction.DEFAULT

    return com.vbogd.terminals.domain.model.Terminal(
        id = id,
        name = name,
        address = address,
        workHours = calcSchedule.arrival,
        distance = 0.0,
        imageAddress = try {
            maps.width.x640.height.x640.url
        } catch (e: Exception) {
            ""
        },
        direction = direction
    )
}