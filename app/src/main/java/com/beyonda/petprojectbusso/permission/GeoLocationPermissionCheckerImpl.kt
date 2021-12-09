package com.beyonda.petprojectbusso.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.beyonda.location.api.permissions.GeoLocationPermissionChecker
import java.util.jar.Manifest

/**
 * Created by Sergei
 */
class GeoLocationPermissionCheckerImpl(val context: Context) : GeoLocationPermissionChecker {
    override val isPermissionGiven: Boolean
        get() = ContextCompat.checkSelfPermission(
            context,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
}