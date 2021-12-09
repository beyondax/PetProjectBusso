package com.beyonda.petprojectbusso.ui.busarrival

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beyonda.petprojectbusso.R
import com.google.android.material.snackbar.Snackbar


/** The ViewBinder for the BusArrival screen */
class BusArrivalViewBinderImpl : BusArrivalViewBinder {

  private lateinit var busArrivalRecyclerView: RecyclerView
  private val busArrivalsAdapter = BusArrivalListAdapter()

  private lateinit var busStopIndicator: TextView
  private lateinit var busStopName: TextView
  private lateinit var busStopDistance: TextView
  private lateinit var busStopDirection: TextView

  override fun init(rootView: View) {
    with(rootView) {
      busStopIndicator = findViewById(R.id.bus_stop_indicator)
      busStopName = findViewById(R.id.bus_stop_item_name)
      busStopDistance = findViewById(R.id.bus_stop_item_distance)
      busStopDirection = findViewById(R.id.bus_stop_item_direction)
      busArrivalRecyclerView = findViewById(R.id.busarrival_recyclerview)
      initRecyclerView(busArrivalRecyclerView)
    }
  }

  /** Display the BusArrival information */
  override fun displayBusArrival(arrivals: BusArrivalsViewModel) {
    with(arrivals.busStop) {
      busStopIndicator.text = stopIndicator
      busStopName.text = stopName
      busStopDistance.text = stopDistance
      busStopDirection.text = stopDirection
    }
    busArrivalsAdapter.submitList(arrivals.arrivals)
  }

  /** Display the error message */
  override fun handleBusArrivalError(error: Throwable) {
    Snackbar.make(
        busArrivalRecyclerView,
        "$error.localizedMessage",
        Snackbar.LENGTH_LONG
    ).show()
  }

  private fun initRecyclerView(busStopRecyclerView: RecyclerView) {
    busArrivalRecyclerView.apply {
      val viewManager = LinearLayoutManager(busStopRecyclerView.context)
      layoutManager = viewManager
      adapter = busArrivalsAdapter
    }
  }
}