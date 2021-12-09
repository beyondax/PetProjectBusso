package com.beyonda.petprojectbusso.ui.view.busstop

import com.beyonda.location.api.model.LocationEvent
import com.beyonda.location.api.model.LocationNotAvailable
import com.beyonda.petprojectbusso.network.BussoEndpoint
import com.beyonda.petprojectbusso.utils.RxSchedulersExtensions
import com.beyonda.petprojectbusso.utils.RxSchedulersRule
import com.beyonda.ui.navigation.Navigator
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner

/**
 * Created by Sergei
 */
internal class BusStopListPresenterImplTest {

    @get:Rule
    var rxRule = RxSchedulersRule()

    lateinit var presenter: BusStopListPresenterImpl
    lateinit var navigator: Navigator
    lateinit var locationObservable: PublishSubject<LocationEvent>
    lateinit var bussoEndpoint: BussoEndpoint
    lateinit var busStopListViewBinder: BusStopListViewBinder


    @Before
    fun setUp() {
        navigator = mock(Navigator::class.java)
        locationObservable = PublishSubject.create()
        bussoEndpoint = mock(BussoEndpoint::class.java)
        busStopListViewBinder = mock(BusStopListViewBinder::class.java)
        presenter = BusStopListPresenterImpl(
            navigator,
            locationObservable,
            bussoEndpoint,
        )
        presenter.bind(busStopListViewBinder)
    }

    @Test
    fun start_whenLocationNotAvailable_displayErrorMessageInvoked() {
        presenter.start()
        locationObservable.onNext(LocationNotAvailable("Provider"))

        verify(busStopListViewBinder).displayErrorMessage("Location Not Available!")
    }
}