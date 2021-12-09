package com.beyonda.petprojectbusso.ui.view.splash

import com.beyonda.mvp.Presenter

/** The Presenter for the SplashActivity */
interface SplashPresenter : Presenter<SplashActivity, SplashViewBinder> {

  fun start()

  fun stop()
}