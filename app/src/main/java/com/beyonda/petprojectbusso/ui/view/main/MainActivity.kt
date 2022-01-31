package com.beyonda.petprojectbusso.ui.view.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.beyonda.petprojectbusso.R
import com.beyonda.petprojectbusso.di.AppComponent
import com.beyonda.petprojectbusso.di.DaggerAppComponent
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    lateinit var comp: AppComponent

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        comp = DaggerAppComponent
            .factory()
            .create(this)
            .apply { inject(this@MainActivity) }
        if (savedInstanceState == null) {
            mainPresenter.goToBusStopList()
        }
    }
}

val Context.comp: AppComponent?
    get() = if (this is MainActivity) comp else null