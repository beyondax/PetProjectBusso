package com.beyonda.petprojectbusso.ui.view.busstop

import android.app.Activity
import android.content.Context
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.beyonda.petprojectbusso.R
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController

/**
 * Created by Sergei
 */
@RunWith(RobolectricTestRunner::class)
internal class BusStopListViewBinderImplTest {
    private lateinit var busStopListViewBinder: BusStopListViewBinder
    private lateinit var fakeBusStopItemSelectedListener: FakeBusStopItemSelectedListener
    private lateinit var activityController: ActivityController<Activity>
    private lateinit var testData: List<BusStopViewModel>

    @Before
    fun setUp() {
        activityController = Robolectric.buildActivity(
            Activity::class.java
        )
        testData = createTestData()
        fakeBusStopItemSelectedListener = FakeBusStopItemSelectedListener()
        busStopListViewBinder = BusStopListViewBinderImpl(fakeBusStopItemSelectedListener)
    }


    @Test
    fun displayBusStopList_whenInvoked_adapterContainsData() {
        val rootView = createLayoutForTest(activityController.get())
        with(busStopListViewBinder) {
            init(rootView)
            displayBusStopList(testData)
        }
        val adapter = rootView.findViewById<RecyclerView>(R.id.busstop_recyclerview).adapter!!
        assertEquals(3, adapter.itemCount)
    }

    // 2
    @Test
    fun busStopItemSelectedListener_whenBusStopSelected_onBusStopSelectedIsInvoked() {
        val testData = createTestData()
        val activity = activityController.get()
        val rootView = createLayoutForTest(activity)
        activity.setContentView(rootView)
        activityController.create().start().visible();
        with(busStopListViewBinder) {
            init(rootView)
            displayBusStopList(testData)
        }
        rootView.findViewById<RecyclerView>(R.id.busstop_recyclerview).getChildAt(2).performClick()
        assertEquals(testData[2], fakeBusStopItemSelectedListener.onBusStopSelectedInvokedWith)
    }

    private class FakeBusStopItemSelectedListener :
        BusStopListViewBinder.BusStopSelectedListener {

        var onBusStopSelectedInvokedWith: BusStopViewModel? = null
        var retryInvoked = false

        override fun onBusStopSelected(busStopViewModel: BusStopViewModel) {
            onBusStopSelectedInvokedWith = busStopViewModel
        }

        override fun retry() {
            retryInvoked = true
        }
    }

    private fun createTestData() = listOf(
        createBusStopViewModelForTest("1"),
        createBusStopViewModelForTest("2"),
        createBusStopViewModelForTest("3"),
    )

    private fun createBusStopViewModelForTest(id: String) = BusStopViewModel(
        "stopId $id",
        "stopName $id",
        "stopDirection $id",
        "stopIndicator $id",
        "stopDistance $id"
    )

    private fun createLayoutForTest(context: Context) = LinearLayout(context)
        .apply {
            addView(RecyclerView(context).apply {
                id = R.id.busstop_recyclerview
            })
        }
}