package com.beyonda.petprojectbusso.model

import com.beyonda.location.api.model.GeoLocation

/**
 * Created by Sergei
 */
data class BusStop(
    val id: String,
    val name: String,
    val location: GeoLocation,
    val direction: String?,
    val indicator: String?,
    val distance: Float?
)
