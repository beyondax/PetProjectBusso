package com.beyonda.petprojectbusso.ui.busarrival

import android.view.View
import com.beyonda.mvp.impl.BasePresenter
import com.beyonda.petprojectbusso.network.BussoEndpoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BusArrivalPresenterImpl constructor(
    private val bussoEndpoint: BussoEndpoint
) : BasePresenter<View, BusArrivalViewBinder>(),
    BusArrivalPresenter {

  private val disposables = CompositeDisposable()

  override fun fetchBusArrival(stopId: String) {
    disposables.add(
        bussoEndpoint
            .findArrivals(stopId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(::mapBusArrivals)
            .subscribe(::displayBusArrival, ::handleBusArrivalError)
    )
  }

  override fun stop() {
    disposables.clear()
  }

  private fun displayBusArrival(arrivals: BusArrivalsViewModel) {
    useViewBinder {
      displayBusArrival(arrivals)
    }
  }

  fun handleBusArrivalError(error: Throwable) {
    useViewBinder {
      handleBusArrivalError(error)
    }
  }
}