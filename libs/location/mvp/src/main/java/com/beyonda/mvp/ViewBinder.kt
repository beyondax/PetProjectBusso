package com.beyonda.mvp

/** ViewBinder Abstraction */
interface ViewBinder<V> {

  /** Initialize the CounterViewBinder implementation */
  fun init(rootView: V)
}