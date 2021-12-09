package com.beyonda.petprojectbusso.utils

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Created by Sergei
 */
class RxSchedulersExtensions : BeforeEachCallback, AfterEachCallback {

    override fun beforeEach(context: ExtensionContext?) {
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
    }

    override fun afterEach(context: ExtensionContext?) {
        RxAndroidPlugins.reset()
        RxJavaPlugins.reset()
    }
}
class RxSchedulersRule : TestRule {
    override fun apply(base: Statement, description: Description?): Statement {
        return RxSchedulerStatement(base = base)
    }

    private inner class RxSchedulerStatement(
        private val base: Statement
        ) : Statement() {

        override fun evaluate() {
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
            try {
                base.evaluate()
            } finally {
                RxAndroidPlugins.reset()
                RxJavaPlugins.reset()
            }
        }
    }
}

