package com.beyonda.petprojectbusso.ui.busarrival

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beyonda.petprojectbusso.R
import com.beyonda.petprojectbusso.di.injectors.BusArrivalFragmentInjector


/**
 * The Fragment for displaying the arrival time
 */
class BusArrivalFragment : Fragment() {

  lateinit var busArrivalViewBinder: BusArrivalViewBinder
  lateinit var busArrivalPresenter: BusArrivalPresenter

  companion object {
    const val BUS_STOP_ID = "BUS_STOP_ID"
  }

  override fun onAttach(context: Context) {
    BusArrivalFragmentInjector.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_busarrival_layout, container, false).apply {
    busArrivalViewBinder.init(this)
  }

  override fun onStart() {
    super.onStart()
    busArrivalPresenter.bind(busArrivalViewBinder)
    val busStopId = arguments?.getString(BUS_STOP_ID) ?: ""
    busArrivalPresenter.fetchBusArrival(busStopId)
  }

  override fun onStop() {
    busArrivalPresenter.unbind()
    busArrivalPresenter.stop()
    super.onStop()
  }
}