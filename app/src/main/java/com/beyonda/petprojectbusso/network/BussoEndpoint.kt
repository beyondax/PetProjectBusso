package com.beyonda.petprojectbusso.network

import android.content.Context
import com.beyonda.petprojectbusso.conf.BUSSO_SERVER_BASE_URL
import com.beyonda.petprojectbusso.model.BusArrivals
import com.beyonda.petprojectbusso.model.BusStop
import com.google.gson.GsonBuilder
import io.reactivex.Single
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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


fun provideBussoEndPoint(context: Context): BussoEndpoint {
    val cache = Cache(context.cacheDir, CACHE_SIZE)
    val okHttpClient = OkHttpClient.Builder()
        .cache(cache)
        .build()
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BUSSO_SERVER_BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()
            )
        )
        .client(okHttpClient)
        .build()
    return retrofit.create(BussoEndpoint::class.java)
}