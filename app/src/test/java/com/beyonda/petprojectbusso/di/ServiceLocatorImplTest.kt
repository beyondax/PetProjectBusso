package com.beyonda.petprojectbusso.di

import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Test for [ServiceLocatorImpl]
 * Created by Sergei
 */
@RunWith(RobolectricTestRunner::class)
class ServiceLocatorImplTest {

    @Rule
    @JvmField
    var thrown: ExpectedException = ExpectedException.none()

    private lateinit var serviceLocator: ServiceLocatorImpl

    @Before
    fun setUp() {
        serviceLocator = ServiceLocatorImpl(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun lookUp_whenObjectIsMissing_throwsException() {

        thrown.expect(IllegalArgumentException::class.java)
        serviceLocator.lookUp<Any>("MISSING")

    }
}