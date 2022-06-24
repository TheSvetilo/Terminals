package com.vbogd.terminals.data.terminalRepository.remote.dao

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
    return com.vbogd.terminals.domain.model.Terminal(
        id = id,
        name = name,
        address = address,
        workHours = calcSchedule.arrival,
        distance = 0.0,
        imageAddress = "",
        direction = false
    )
}