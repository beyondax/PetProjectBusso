package com.beyonda.petprojectbusso.di

import android.app.Activity
import android.content.Context
import android.location.LocationManager
import com.beyonda.location.api.model.LocationEvent
import com.beyonda.location.rx.provideRxLocationObservable
import com.beyonda.petprojectbusso.permission.GeoLocationPermissionCheckerImpl
import com.beyonda.petprojectbusso.ui.view.main.MainPresenter
import com.beyonda.petprojectbusso.ui.view.main.MainPresenterImpl
import com.beyonda.petprojectbusso.ui.view.splash.SplashPresenter
import com.beyonda.petprojectbusso.ui.view.splash.SplashPresenterImpl
import com.beyonda.petprojectbusso.ui.view.splash.SplashViewBinder
import com.beyonda.petprojectbusso.ui.view.splash.SplashViewBinderImpl
import com.beyonda.ui.navigation.Navigator
import com.beyonda.ui.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Observable


@Module(includes = [
    AppModule.Bindings::class
])
class AppModule(
    private val activity: Activity
) {


    @Provides
    fun provideLocationObservable(): Observable<LocationEvent> {
        val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val geoLocationPermissionChecker = GeoLocationPermissionCheckerImpl(activity)
        return provideRxLocationObservable(locationManager, geoLocationPermissionChecker)

    }

    @Provides
    fun provideNavigator(): Navigator = NavigatorImpl(activity)

    @Module
    interface Bindings {

        @Binds
        fun bindSplashPresenter(impl: SplashPresenterImpl): SplashPresenter

        @Binds
        fun bindSplashViewBinder(impl: SplashViewBinderImpl): SplashViewBinder

        @Binds
        fun bindMainPresenter(impl: MainPresenterImpl): MainPresenter
    }
}