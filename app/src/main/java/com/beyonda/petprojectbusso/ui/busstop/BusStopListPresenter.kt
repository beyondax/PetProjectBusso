package com.beyonda.petprojectbusso.ui.busstop

import android.view.View
import com.beyonda.mvp.Presenter

/** The Presenter for the BusStopFragment */
interface BusStopListPresenter : Presenter<View, BusStopListViewBinder>,
    BusStopListViewBinder.BusStopItemSelectedListener {

  fun start()

  fun stop()
}