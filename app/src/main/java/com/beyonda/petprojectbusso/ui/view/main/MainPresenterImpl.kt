package com.beyonda.petprojectbusso.ui.view.main

import com.beyonda.petprojectbusso.R
import com.beyonda.petprojectbusso.ui.busstop.BusStopFragment
import com.beyonda.ui.navigation.FragmentDestination
import com.beyonda.ui.navigation.Navigator
import javax.inject.Inject


/** MainPresenter implementation */
class MainPresenterImpl @Inject constructor(
    private val navigator: Navigator
) : MainPresenter {
  override fun goToBusStopList() {
    navigator.navigateTo(FragmentDestination(BusStopFragment(), R.id.anchor_point))
  }
}