package com.beyonda.petprojectbusso.ui.busarrival

import com.beyonda.petprojectbusso.model.BusArrival
import com.beyonda.petprojectbusso.model.BusArrivalGroup
import com.beyonda.petprojectbusso.model.BusArrivals
import com.beyonda.petprojectbusso.ui.busstop.mapBusStop
import java.text.SimpleDateFormat
import java.util.*

/**
 * The ViewModel for the BusArrivals
 */
internal fun mapBusArrivals(busArrivals: BusArrivals): BusArrivalsViewModel =
    BusArrivalsViewModel(
        mapBusStop(busArrivals.busStop),
        busArrivals
            .arrivalGroups
            .map(::mapBusArrivalGroup)
    )

/**
 * Maps the BusArrivalGroup into a BusArrivalGroupViewModel adding some decorations
 */
internal fun mapBusArrivalGroup(busArrivalGroup: BusArrivalGroup): BusArrivalGroupViewModel {
  return BusArrivalGroupViewModel(
      lineName = busArrivalGroup.lineName,
      destination = busArrivalGroup.destination,
      arrivals = busArrivalGroup.arrivals.map(::mapBusArrival)
  )
}

/**
 * Maps an arrival times group into its viewmodel
 */
internal fun mapBusArrival(arrival: BusArrival): BusArrivalViewModel =
    BusArrivalViewModel(
        expectedTime = expectedTime(arrival.expectedArrival),
        vehicleId = arrival.vehicleId ?: "-",
        destination = arrival.destinationName
    )

val DATE_FORMATTER = SimpleDateFormat("HH:mm", Locale.ENGLISH)

private fun expectedTime(expectedTime: Date) = DATE_FORMATTER.format(expectedTime)
