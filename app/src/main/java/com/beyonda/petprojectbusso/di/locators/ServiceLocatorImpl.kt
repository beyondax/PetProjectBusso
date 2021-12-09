package com.beyonda.petprojectbusso.di.locators

import android.content.Context
import android.location.LocationManager
import com.beyonda.location.rx.provideRxLocationObservable
import com.beyonda.petprojectbusso.network.provideBussoEndPoint
import com.beyonda.petprojectbusso.permission.GeoLocationPermissionCheckerImpl


const val BUSSO_ENDPOINT = "BussoEndpoint"
const val LOCATION_OBSERVABLE = "LocationObservable"
const val ACTIVITY_LOCATOR_FACTORY = "ActivityLocatorFactory"

class ServiceLocatorImpl(
    val context: Context
) : ServiceLocator {

  private val locationManager =
      context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
  private val geoLocationPermissionChecker = GeoLocationPermissionCheckerImpl(context)
  private val locationObservable =
      provideRxLocationObservable(locationManager, geoLocationPermissionChecker)
  private val bussoEndpoint = provideBussoEndPoint(context)

  @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
  override fun <A : Any> lookUp(name: String): A = when (name) {
    LOCATION_OBSERVABLE -> locationObservable
    BUSSO_ENDPOINT -> bussoEndpoint
    ACTIVITY_LOCATOR_FACTORY -> activityServiceLocatorFactory(this)
    else -> throw IllegalArgumentException("No component lookup for the key: $name")
  } as A
}