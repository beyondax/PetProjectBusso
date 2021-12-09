package com.beyonda.petprojectbusso.ui.busstop

import android.view.View
import com.beyonda.mvp.ViewBinder

/** The ViewBinder implementation for the BusStopFragment */
interface BusStopListViewBinder : ViewBinder<View> {

  /**
   * Displays the list of BusStop information
   */
  fun displayBusStopList(busStopList: List<BusStopViewModel>)

  /**
   * Displays an error message
   */
  fun displayErrorMessage(msg: String)

  /**
   * Interface to implement to observe the BusStop selection
   */
  interface BusStopItemSelectedListener {

    /**
     * Invoked when the BusStop is selected
     */
    fun onBusStopSelected(busStopViewModel: BusStopViewModel)

    /**
     * Invoked when the retry option is selected
     */
    fun retry()
  }
}