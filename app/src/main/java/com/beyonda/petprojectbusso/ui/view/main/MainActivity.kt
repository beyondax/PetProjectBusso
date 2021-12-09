package com.beyonda.petprojectbusso.ui.view.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.beyonda.petprojectbusso.R
import com.beyonda.petprojectbusso.di.AppModule
import com.beyonda.petprojectbusso.di.DaggerAppComponent
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

  @Inject
  lateinit var mainPresenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    DaggerAppComponent
      .builder()
      .appModule(AppModule(this))
      .build()
      .inject(this)
    if (savedInstanceState == null) {
      mainPresenter.goToBusStopList()
    }
  }
}