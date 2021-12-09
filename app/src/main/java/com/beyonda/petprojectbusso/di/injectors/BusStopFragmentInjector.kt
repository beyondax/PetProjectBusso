package com.beyonda.petprojectbusso.di.injectors

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.beyonda.petprojectbusso.di.locators.*
import com.beyonda.petprojectbusso.lookUp
import com.beyonda.petprojectbusso.ui.busstop.BusStopFragment

object BusStopFragmentInjector : Injector<BusStopFragment> {
  override fun inject(target: BusStopFragment) {
    val parentActivity = target.context as AppCompatActivity
    val activityServiceLocator =
        parentActivity.lookUp<ServiceLocatorFactory<AppCompatActivity>>(ACTIVITY_LOCATOR_FACTORY)
            .invoke(parentActivity)
    val fragmentServiceLocator =
        activityServiceLocator.lookUp<ServiceLocatorFactory<Fragment>>(FRAGMENT_LOCATOR_FACTORY)
            .invoke(target)
    with(target) {
      busStopListPresenter = fragmentServiceLocator.lookUp(BUSSTOP_LIST_PRESENTER)
      busStopListViewBinder = fragmentServiceLocator.lookUp(BUSSTOP_LIST_VIEWBINDER)
    }
  }
}