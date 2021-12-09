package com.beyonda.mvp.impl

import androidx.annotation.CallSuper
import com.beyonda.mvp.Presenter
import com.beyonda.mvp.ViewBinder

/**
 * Created by Sergei
 */
abstract class BasePresenter<V, VB : ViewBinder<V>> : Presenter<V, VB> {

    private var viewBinder: VB? = null

    @CallSuper
    override fun bind(viewBinder: VB) {
        this.viewBinder = viewBinder
    }

    @CallSuper
    override fun unbind() {
       viewBinder = null
    }

    protected fun useViewBinder(consumer: VB.() -> Unit) {
            viewBinder?.run {
                consumer.invoke(this)
            }
    }
}