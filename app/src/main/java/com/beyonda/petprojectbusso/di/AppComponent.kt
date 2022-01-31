package com.beyonda.petprojectbusso.di

import android.app.Activity
import com.beyonda.petprojectbusso.network.NetworkModule
import com.beyonda.petprojectbusso.ui.busarrival.BusArrivalFragment
import com.beyonda.petprojectbusso.ui.busstop.BusStopFragment
import com.beyonda.petprojectbusso.ui.view.main.MainActivity
import com.beyonda.petprojectbusso.ui.view.splash.SplashActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: SplashActivity)

    fun inject(activity: MainActivity)

    fun inject(fragment: BusStopFragment)

    fun inject(fragment: BusArrivalFragment)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance activity: Activity): AppComponent

    }
}