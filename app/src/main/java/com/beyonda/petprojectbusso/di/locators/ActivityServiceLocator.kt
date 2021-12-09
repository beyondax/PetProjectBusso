package com.beyonda.petprojectbusso.di.locators

import androidx.appcompat.app.AppCompatActivity
import com.beyonda.location.api.model.LocationEvent
import com.beyonda.petprojectbusso.ui.view.main.MainPresenter
import com.beyonda.petprojectbusso.ui.view.main.MainPresenterImpl
import com.beyonda.petprojectbusso.ui.view.splash.SplashPresenter
import com.beyonda.petprojectbusso.ui.view.splash.SplashPresenterImpl
import com.beyonda.petprojectbusso.ui.view.splash.SplashViewBinder
import com.beyonda.petprojectbusso.ui.view.splash.SplashViewBinderImpl
import com.beyonda.ui.navigation.Navigator
import com.beyonda.ui.navigation.NavigatorImpl
import io.reactivex.Observable

const val NAVIGATOR = "Navigator"
const val FRAGMENT_LOCATOR_FACTORY = "FragmentLocatorFactory"
const val MAIN_PRESENTER = "MainPresenter"
const val SPLASH_PRESENTER = "SplashPresenter"
const val SPLASH_VIEWBINDER = "SplashViewBinder"

val activityServiceLocatorFactory: (ServiceLocator) -> ServiceLocatorFactory<AppCompatActivity> =
    { fallbackServiceLocator: ServiceLocator ->
      { activity: AppCompatActivity ->
        ActivityServiceLocator(activity).apply {
          applicationServiceLocator = fallbackServiceLocator
        }
      }
    }

class ActivityServiceLocator(
    val activity: AppCompatActivity
) : ServiceLocator {

  var applicationServiceLocator: ServiceLocator? = null
  var mainPresenter: MainPresenter? = null
  var splashPresenter: SplashPresenter? = null
  var splashViewBinder: SplashViewBinder? = null

  @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
  override fun <A : Any> lookUp(name: String): A = when (name) {
    NAVIGATOR -> NavigatorImpl(activity)
    FRAGMENT_LOCATOR_FACTORY -> fragmentServiceLocatorFactory(this)
    SPLASH_VIEWBINDER -> {
      if (splashViewBinder == null) {
        val navigator: Navigator = lookUp(NAVIGATOR)
        splashViewBinder = SplashViewBinderImpl(
            navigator
        )
      }
      splashViewBinder
    }
    SPLASH_PRESENTER -> {
      if (splashPresenter == null) {
        val locationObservable: Observable<LocationEvent> = applicationServiceLocator!!.lookUp(
          LOCATION_OBSERVABLE
        )
        splashPresenter = SplashPresenterImpl(
            locationObservable
        )
      }
      splashPresenter
    }
    MAIN_PRESENTER -> {
      if (mainPresenter == null) {
        val navigator: Navigator = lookUp(NAVIGATOR)
        mainPresenter = MainPresenterImpl(navigator)
      }
      mainPresenter
    }
    else -> applicationServiceLocator?.lookUp<A>(name)
        ?: throw IllegalArgumentException("No component lookup for the key: $name")
  } as A
}