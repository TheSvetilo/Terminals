package com.vbogd.terminals.utils

import kotlin.math.*

class DistanceCalculator {

    fun toDistance(
        latitudeFrom: Double,
        longitudeFrom: Double,
        latitudeTo: Double,
        longitudeTo: Double
    ): Int {

        val latDistance = Math.toRadians(latitudeFrom - latitudeTo)
        val longDistance = Math.toRadians(longitudeFrom - longitudeTo)

        val formula = (sin(latDistance / 2) * sin(latDistance / 2)
                + (cos(Math.toRadians(latitudeFrom)) * cos(Math.toRadians(latitudeTo))
                * sin(longDistance / 2) * sin(longDistance / 2)))

        val formula2 = 2 * atan2(sqrt(formula), sqrt(1 - formula))

        return (AVERAGE_RADIUS_OF_EARTH_KM * formula2).roundToInt()
    }

    companion object {

        const val AVERAGE_RADIUS_OF_EARTH_KM = 6371

        const val HOME_LAT = 59.9949370139697
        const val HOME_LONG = 30.32430948409013

    }
}
