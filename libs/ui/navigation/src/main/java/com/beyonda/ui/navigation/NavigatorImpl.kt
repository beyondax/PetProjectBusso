package com.beyonda.ui.navigation

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Sergei
 */
class NavigatorImpl(private val activity: Activity) : Navigator {
    override fun navigateTo(destination: Destination, params: Bundle?) {
        when (destination) {
            is ActivityIntentDestination -> {
                activity.startActivity(destination.intent)
            }
            is FragmentDestination<*> -> {
                val builder = (activity as AppCompatActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(destination.anchorId, destination.fragment)
                destination.withBackStack?.run {
                    builder.addToBackStack(this)
                }
                builder.commit()
            }
            is FragmentFactoryDestination<*> -> {
                val builder = (activity as AppCompatActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(destination.anchorId, destination.fragmentFactory(destination.bundle))
                destination.withBackStack?.run {
                    builder.addToBackStack(this)
                }
                builder.commit()
            }
            is ActivityBackDestination -> activity.finish()
            is FragmentBackDestination -> (activity as AppCompatActivity)
                .supportFragmentManager
                .popBackStack()
        }
    }
}