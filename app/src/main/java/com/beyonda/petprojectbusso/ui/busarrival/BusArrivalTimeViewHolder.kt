package com.beyonda.petprojectbusso.ui.busarrival

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.beyonda.petprojectbusso.R

class BusArrivalTimeViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

  private val extectedTime = itemView.findViewById<TextView>(R.id.arrival_time)

  fun bind(arrival: BusArrivalViewModel) {
    extectedTime.text = arrival.expectedTime
  }
}