package com.beyonda.petprojectbusso.di

import com.beyonda.petprojectbusso.ui.view.main.MainActivity
import com.beyonda.petprojectbusso.ui.view.splash.SplashActivity
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: MainActivity)
}