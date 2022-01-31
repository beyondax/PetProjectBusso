package com.beyonda.petprojectbusso.di

import android.app.Activity
import android.content.Context
import android.location.LocationManager
import com.beyonda.location.api.model.LocationEvent
import com.beyonda.location.rx.provideRxLocationObservable
import com.beyonda.petprojectbusso.permission.GeoLocationPermissionCheckerImpl
import com.beyonda.petprojectbusso.ui.busarrival.BusArrivalPresenter
import com.beyonda.petprojectbusso.ui.busarrival.BusArrivalPresenterImpl
import com.beyonda.petprojectbusso.ui.busarrival.BusArrivalViewBinder
import com.beyonda.petprojectbusso.ui.busarrival.BusArrivalViewBinderImpl
import com.beyonda.petprojectbusso.ui.busstop.BusStopListPresenter
import com.beyonda.petprojectbusso.ui.busstop.BusStopListPresenterImpl
import com.beyonda.petprojectbusso.ui.busstop.BusStopListViewBinder
import com.beyonda.petprojectbusso.ui.busstop.BusStopListViewBinderImpl
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
class AppModule {

    @Provides
    fun provideNavigator(activity: Activity): Navigator = NavigatorImpl(activity)

    @Provides
    fun provideLocationObservable(activity: Activity): Observable<LocationEvent> {
        val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val geoLocationPermissionChecker = GeoLocationPermissionCheckerImpl(activity)
        return provideRxLocationObservable(locationManager, geoLocationPermissionChecker)
    }

    @Module
    interface Bindings {

        @Binds
        fun bindSplashPresenter(impl: SplashPresenterImpl): SplashPresenter

        @Binds
        fun bindSplashViewBinder(impl: SplashViewBinderImpl): SplashViewBinder

        @Binds
        fun bindMainPresenter(impl: MainPresenterImpl): MainPresenter

        @Binds
        fun bindBusStopListViewBinder(impl: BusStopListViewBinderImpl): BusStopListViewBinder

        @Binds
        fun bindBusStopListPresenter(impl: BusStopListPresenterImpl): BusStopListPresenter

        @Binds
        fun bindBusStopListViewBinderListener(impl: BusStopListPresenterImpl): BusStopListViewBinder.BusStopItemSelectedListener

        @Binds
        fun bindBusArrivalPresenter(impl: BusArrivalPresenterImpl): BusArrivalPresenter

        @Binds
        fun bindBusArrivalViewBinder(impl: BusArrivalViewBinderImpl): BusArrivalViewBinder
    }
}