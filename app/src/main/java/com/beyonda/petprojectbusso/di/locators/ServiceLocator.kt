package com.beyonda.petprojectbusso.di.locators

/**
 * This is the abstraction for the ServiceLocator design pattern implementation
 */
interface ServiceLocator {
  /**
   * Returns the object of type A bound to a specific name
   */
  fun <A : Any> lookUp(name: String): A
}

typealias ServiceLocatorFactory<A> = (A) -> ServiceLocator