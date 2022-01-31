package com.beyonda.petprojectbusso.ui.busstop

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.beyonda.petprojectbusso.R
import com.beyonda.petprojectbusso.ui.view.main.comp
import javax.inject.Inject

/**
 * The Fragment which displays the list of BusStop close to the
 */
class BusStopFragment : Fragment() {

  @Inject
  lateinit var busStopListViewBinder: BusStopListViewBinder
  @Inject
  lateinit var busStopListPresenter: BusStopListPresenter

  override fun onAttach(context: Context) {
    context.comp?.inject(this)
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