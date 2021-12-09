package com.beyonda.petprojectbusso.model

import java.util.*

/**
 * Created by Sergei
 */
data class BusArrivals(
    val busStop: BusStop,
    val arrivalGroups: List<BusArrivalGroup>
)

data class BusArrivalGroup(
    val lineId: String,
    val lineName: String,
    val destination: String,
    val arrivals: List<BusArrival>
)

data class BusArrival(
    val id: String,
    val vehicleId: String?,
    val lineId: String,
    val lineName: String,
    val destinationName: String,
    val expectedArrival: Date
)