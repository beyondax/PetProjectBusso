package com.beyonda.petprojectbusso.di.locators

import androidx.fragment.app.Fragment
import com.beyonda.location.api.model.LocationEvent
import com.beyonda.petprojectbusso.network.BussoEndpoint
import com.beyonda.petprojectbusso.ui.busstop.BusStopListViewBinderImpl
import com.beyonda.ui.navigation.Navigator
import com.beyonda.petprojectbusso.ui.busarrival.BusArrivalPresenter
import com.beyonda.petprojectbusso.ui.busarrival.BusArrivalPresenterImpl
import com.beyonda.petprojectbusso.ui.busarrival.BusArrivalViewBinder
import com.beyonda.petprojectbusso.ui.busarrival.BusArrivalViewBinderImpl
import com.beyonda.petprojectbusso.ui.busstop.BusStopListPresenter
import com.beyonda.petprojectbusso.ui.busstop.BusStopListPresenterImpl
import com.beyonda.petprojectbusso.ui.busstop.BusStopListViewBinder
import io.reactivex.Observable

const val BUSSTOP_LIST_PRESENTER = "BusStopListPresenter"
const val BUSSTOP_LIST_VIEWBINDER = "BusStopListViewBinder"
const val BUS_ARRIVAL_PRESENTER = "BusArrivalPresenter"
const val BUS_ARRIVAL_VIEWBINDER = "BusArrivalViewBinder"

val fragmentServiceLocatorFactory: (ServiceLocator) -> ServiceLocatorFactory<Fragment> =
    { fallbackServiceLocator: ServiceLocator ->
      { fragment: Fragment ->
        FragmentServiceLocator(fragment).apply {
          activityServiceLocator = fallbackServiceLocator
        }
      }
    }

class FragmentServiceLocator(
    val fragment: Fragment
) : ServiceLocator {

  var activityServiceLocator: ServiceLocator? = null
  var busStopListPresenter: BusStopListPresenter? = null
  var busStopListViewBinder: BusStopListViewBinder? = null
  var busArrivalPresenter: BusArrivalPresenter? = null
  var busArrivalViewBinder: BusArrivalViewBinder? = null

  @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
  override fun <A : Any> lookUp(name: String): A = when (name) {
    BUSSTOP_LIST_PRESENTER -> {
      if (busStopListPresenter == null) {
        val navigator: Navigator = activityServiceLocator!!.lookUp(NAVIGATOR)
        val locationObservable: Observable<LocationEvent> = activityServiceLocator!!.lookUp(
            LOCATION_OBSERVABLE
        )
        val bussoEndpoint: BussoEndpoint = activityServiceLocator!!.lookUp(BUSSO_ENDPOINT)
        busStopListPresenter = BusStopListPresenterImpl(
            navigator,
            locationObservable,
            bussoEndpoint
        )
      }
      busStopListPresenter
    }
    BUSSTOP_LIST_VIEWBINDER -> {
      if (busStopListViewBinder == null) {
        val busStopListPresenter: BusStopListPresenter = lookUp(BUSSTOP_LIST_PRESENTER)
        busStopListViewBinder = BusStopListViewBinderImpl(busStopListPresenter)
      }
      busStopListViewBinder
    }
    BUS_ARRIVAL_PRESENTER -> {
      if (busArrivalPresenter == null) {
        val bussoEndpoint: BussoEndpoint = activityServiceLocator!!.lookUp(BUSSO_ENDPOINT)
        busArrivalPresenter = BusArrivalPresenterImpl(
            bussoEndpoint
        )
      }
      busArrivalPresenter
    }
    BUS_ARRIVAL_VIEWBINDER -> {
      if (busArrivalViewBinder == null) {
        busArrivalViewBinder = BusArrivalViewBinderImpl()
      }
      busArrivalViewBinder
    }
    else -> activityServiceLocator?.lookUp<A>(name)
        ?: throw IllegalArgumentException("No component lookup for the key: $name")
  } as A
}