package com.beyonda.petprojectbusso.ui.view.splash

import com.beyonda.mvp.ViewBinder

/** The ViewBinder for the SplashActivity */
interface SplashViewBinder : ViewBinder<SplashActivity> {

  fun goToMain()

  fun handleError(error: Throwable)

  fun requestLocationPermission()

  fun onRequestPermissionsResult(
      requestCode: Int,
      permissions: Array<String>,
      grantResults: IntArray
  )
}