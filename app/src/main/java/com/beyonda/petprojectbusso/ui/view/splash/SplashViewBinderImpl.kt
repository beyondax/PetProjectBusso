package com.beyonda.petprojectbusso.ui.view.splash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.beyonda.petprojectbusso.ui.view.main.MainActivity
import com.beyonda.ui.navigation.ActivityIntentDestination
import com.beyonda.ui.navigation.Navigator
import javax.inject.Inject

/** The ViewBinder implementation for the SplashActivity */
class SplashViewBinderImpl @Inject constructor(
    private val navigator: Navigator
) : SplashViewBinder {

  companion object {
    private const val LOCATION_PERMISSION_REQUEST_ID = 1
  }

  private val handler = Handler(Looper.getMainLooper())
  private lateinit var splashActivity: SplashActivity

  override fun init(rootView: SplashActivity) {
    splashActivity = rootView
  }

  override fun goToMain() {
    handler.post {
      navigator.navigateTo(
          ActivityIntentDestination(
              Intent(splashActivity, MainActivity::class.java)
          )
      )
      with(splashActivity) {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
      }
    }
  }

  override fun handleError(error: Throwable) {
    TODO("Not yet implemented")
  }

  override fun requestLocationPermission() {
    ActivityCompat.requestPermissions(
        splashActivity,
        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
        LOCATION_PERMISSION_REQUEST_ID
    )
  }

  override fun onRequestPermissionsResult(
      requestCode: Int,
      permissions: Array<String>,
      grantResults: IntArray
  ) {
    when (requestCode) {
      LOCATION_PERMISSION_REQUEST_ID -> {
        // If request is cancelled, the result arrays are empty.
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
          // Permission granted! We go on!
          goToMain()
        } else {
          // Request denied, we request again
          requestLocationPermission()
        }
      }
    }
  }
}