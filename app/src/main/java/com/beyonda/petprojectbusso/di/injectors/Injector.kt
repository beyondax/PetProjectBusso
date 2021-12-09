package com.beyonda.petprojectbusso.di.injectors

interface Injector<A> {

  fun inject(target: A)
}

