package com.beyonda.petprojectbusso.ui.view.busstop

import com.beyonda.location.api.model.GeoLocation
import com.beyonda.petprojectbusso.model.BusStop
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Sergei
 */
class BusStopMapperKtTest {

    @Test
    fun mapBusStop_returnsBusStopViewModel() {

        val busStop = BusStop(
            "id",
            "name",
            GeoLocation(0.0,0.0),
            "direction",
            "indicator",
            123F
        )

       val expectedViewModel = BusStopViewModel(
               "id",
               "name",
               "direction",
               "indicator",
               "123 m"
       )

        assertEquals(expectedViewModel, mapBusStop(busStop))
    }
}