package com.beyonda.petprojectbusso

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.beyonda.petprojectbusso.di.locators.ServiceLocator
import com.beyonda.petprojectbusso.di.locators.ServiceLocatorImpl

class Main : Application() {
  lateinit var serviceLocator: ServiceLocator

  override fun onCreate() {
    super.onCreate()
    serviceLocator = ServiceLocatorImpl(this)
  }
}

internal fun <A : Any> AppCompatActivity.lookUp(name: String): A =
    (applicationContext as Main).serviceLocator.lookUp(name)