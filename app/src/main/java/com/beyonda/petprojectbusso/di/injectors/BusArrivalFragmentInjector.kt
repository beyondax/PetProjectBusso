package com.beyonda.petprojectbusso.di.injectors

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.beyonda.petprojectbusso.di.locators.*
import com.beyonda.petprojectbusso.lookUp
import com.beyonda.petprojectbusso.ui.busarrival.BusArrivalFragment


object BusArrivalFragmentInjector : Injector<BusArrivalFragment> {
  override fun inject(target: BusArrivalFragment) {
    val parentActivity = target.context as AppCompatActivity
    val activityServiceLocator =
        parentActivity.lookUp<ServiceLocatorFactory<AppCompatActivity>>(ACTIVITY_LOCATOR_FACTORY)
            .invoke(parentActivity)
    val fragmentServiceLocator =
        activityServiceLocator.lookUp<ServiceLocatorFactory<Fragment>>(FRAGMENT_LOCATOR_FACTORY)
            .invoke(target)
    with(target) {
      busArrivalPresenter = fragmentServiceLocator.lookUp(BUS_ARRIVAL_PRESENTER)
      busArrivalViewBinder = fragmentServiceLocator.lookUp(BUS_ARRIVAL_VIEWBINDER)
    }
  }
}