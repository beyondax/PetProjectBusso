package com.beyonda.location.api.permissions

/**
 * Created by Sergei
 */
interface GeoLocationPermissionChecker {

    /**
     * @return True if the permission has been provided
     */
    val isPermissionGiven: Boolean
}
