package com.beyonda.ui.navigation


import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

/**
 * Created by Sergei
 */

/**
 * Abstraction for the concept of destination
 */
sealed class Destination

/**
 * This is the Destination of a navigation between Activities
 */
data class ActivityIntentDestination(val intent: Intent) : Destination()

/**
 * This is the Destination of a navigation between Fragments
 */
data class FragmentDestination<out T : Fragment>(
    val fragment: T,
    @IdRes val anchorId: Int,
    val withBackStack: String? = null
) : Destination()

/**
 * This is the Destination of a navigation between Fragments using Factory for the
 * Fragment
 */
data class FragmentFactoryDestination<out T : Fragment>(
    val fragmentFactory: (Bundle?) -> T,
    @IdRes val anchorId: Int,
    val withBackStack: String? = null,
    val bundle: Bundle? = null
) : Destination()

/**
 * Finish the current Activity
 */
object ActivityBackDestination : Destination()

/**
 * PushBack the current Fragment
 */
object FragmentBackDestination : Destination()