package com.beyonda.petprojectbusso.network

import com.beyonda.petprojectbusso.conf.BUSSO_SERVER_BASE_URL
import com.beyonda.petprojectbusso.model.BusArrivals
import com.beyonda.petprojectbusso.model.BusStop
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Sergei
 */
private const val CACHE_SIZE = 100 * 1024L

interface BussoEndpoint {

    /**
     * This is the endpoint which returns the list of Bus stop for a given
     * location and radius
     */
    @GET("${BUSSO_SERVER_BASE_URL}findBusStop/{lat}/{lng}")
    fun findBusStopByLocation(
        @Path("lat") latitude: Double,
        @Path("lng") longitude: Double,
        @Query("radius") radius: Int
    ): Single<List<BusStop>>

    /**
     * This is the endpoint which returns the list of Arrival for a given BusStop grouped
     * by line
     */
    @GET("$BUSSO_SERVER_BASE_URL/findBusArrivals/{stopId}")
    fun findArrivals(
        @Path("stopId") stopId: String
    ): Single<BusArrivals>
}