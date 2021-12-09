package com.beyonda.petprojectbusso.ui.view.splash

import com.beyonda.location.api.model.LocationEvent
import com.beyonda.location.api.model.LocationPermissionGranted
import com.beyonda.location.api.model.LocationPermissionRequest
import com.beyonda.mvp.impl.BasePresenter
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/** Presenter implementation for the SplashActivity */
class SplashPresenterImpl @Inject constructor(
    private val locationObservable: Observable<LocationEvent>,
) : BasePresenter<SplashActivity, SplashViewBinder>(), SplashPresenter {

  companion object {
    private const val DELAY_MILLIS = 1000L
  }

  private val disposables = CompositeDisposable()

  override fun start() {
    disposables.add(
        locationObservable
            .delay(DELAY_MILLIS, TimeUnit.MILLISECONDS)
            .filter(::isPermissionEvent)
            .subscribe(::handlePermissionRequest, ::handleError)
    )
  }

  override fun stop() {
    disposables.clear()
  }

  private fun handlePermissionRequest(permissionRequestEvent: LocationEvent) {
    when (permissionRequestEvent) {
      is LocationPermissionRequest -> useViewBinder { requestLocationPermission() }
      is LocationPermissionGranted -> useViewBinder { goToMain() }
      else -> throw IllegalStateException("You should never receive this!")
    }
  }

  private fun isPermissionEvent(locationEvent: LocationEvent) =
      locationEvent is LocationPermissionRequest || locationEvent is LocationPermissionGranted

  private fun handleError(error: Throwable) {
    useViewBinder {
      handleError(error)
    }
  }
}