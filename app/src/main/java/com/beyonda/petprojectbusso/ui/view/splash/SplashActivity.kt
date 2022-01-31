package com.beyonda.petprojectbusso.ui.view.splash

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.beyonda.petprojectbusso.R
import com.beyonda.petprojectbusso.di.DaggerAppComponent
import javax.inject.Inject

/**
 * Splash Screen with the app icon and name at the center, this is also the launch screen and
 * opens up in fullscreen mode. Once launched it waits for 2 seconds after which it opens the
 * MainActivity
 */
class SplashActivity : AppCompatActivity() {

  @Inject
  lateinit var splashViewBinder: SplashViewBinder

  @Inject
  lateinit var splashPresenter: SplashPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    makeFullScreen()
    setContentView(R.layout.activity_splash)
    DaggerAppComponent.factory()
      .create(this)
      .inject(this)

    splashViewBinder.init(this)
  }

  override fun onStart() {
    super.onStart()
    with(splashPresenter) {
      bind(splashViewBinder)
      start()
    }
  }

  override fun onStop() {
    with(splashPresenter) {
      stop()
      unbind()
    }
    super.onStop()
  }

  private fun makeFullScreen() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
    window.setFlags(
        WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN
    )
    supportActionBar?.hide()
  }


  override fun onRequestPermissionsResult(
      requestCode: Int,
      permissions: Array<String>,
      grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    splashViewBinder.onRequestPermissionsResult(requestCode, permissions, grantResults)
  }
}