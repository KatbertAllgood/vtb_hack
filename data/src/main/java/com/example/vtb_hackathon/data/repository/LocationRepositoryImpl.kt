package com.example.vtb_hackathon.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.example.vtb_hackathon.data.entity.CurrentLocationData
import com.example.vtb_hackathon.domain.entity.CurrentLocationDomain
import com.example.vtb_hackathon.domain.repository.LocationRepository
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LocationRepositoryImpl(context: Context) : LocationRepository {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val fastLocationRequests = LocationRequest.Builder(FASTEST_UPDATE_INTERVAL_SECS)
        .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
        .setMaxUpdateDelayMillis(0L)
        .build()


    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Flow<CurrentLocationDomain> = callbackFlow {
        val locationRequest = LocationRequest.Builder(fastLocationRequests).setIntervalMillis(
            UPDATE_INTERVAL_SECS
        ).build()

        val callBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                val location = locationResult.lastLocation
                val userLocation = CurrentLocationData(
                    latitude = location?.latitude,
                    longitude = location?.longitude,
                )
                trySend(userLocation).isSuccess
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            callBack,
            Looper.getMainLooper()
        )
        awaitClose { fusedLocationClient.removeLocationUpdates(callBack) }
    }


    companion object {
        private const val UPDATE_INTERVAL_SECS = 60000L
        private const val FASTEST_UPDATE_INTERVAL_SECS = 1000L
    }
}
