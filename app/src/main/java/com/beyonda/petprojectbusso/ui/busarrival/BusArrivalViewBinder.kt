package com.beyonda.petprojectbusso.ui.busarrival

import android.view.View
import com.beyonda.mvp.ViewBinder

/** The ViewBinder for the BusArrival screen */
interface BusArrivalViewBinder : ViewBinder<View> {

  /** Display the BusArrival information */
  fun displayBusArrival(arrivals: BusArrivalsViewModel)

  /** Display the error message */
  fun handleBusArrivalError(error: Throwable)
}