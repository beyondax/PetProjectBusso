package com.beyonda.petprojectbusso.ui.busarrival

import android.view.View
import com.beyonda.mvp.Presenter

/** The Presenter for the BusArrival Screen */
interface BusArrivalPresenter : Presenter<View, BusArrivalViewBinder> {

  fun fetchBusArrival(budStopId: String)

  fun stop()
}