package com.beyonda.petprojectbusso.ui.busstop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beyonda.petprojectbusso.R
import com.beyonda.petprojectbusso.di.injectors.BusStopFragmentInjector

/**
 * The Fragment which displays the list of BusStop close to the
 */
class BusStopFragment : Fragment() {

  lateinit var busStopListViewBinder: BusStopListViewBinder
  lateinit var busStopListPresenter: BusStopListPresenter

  override fun onAttach(context: Context) {
    BusStopFragmentInjector.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? = inflater.inflate(R.layout.fragment_busstop_layout, container, false).apply {
    busStopListViewBinder.init(this)
  }


  override fun onStart() {
    super.onStart()
    with(busStopListPresenter) {
      bind(busStopListViewBinder)
      start()
    }
  }

  override fun onStop() {
    with(busStopListPresenter) {
      stop()
      unbind()
    }
    super.onStop()
  }
}