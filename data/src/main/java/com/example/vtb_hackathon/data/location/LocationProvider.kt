package com.example.vtb_hackathon.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.example.vtb_hackathon.data.entity.CurrentLocationData
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@SuppressLint("MissingPermission")
class LocationProvider(context: Context) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private lateinit var locationFlow: Flow<CurrentLocationData>

    init {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                locationFlow = flow {
                    emit(CurrentLocationData(location?.longitude, location?.latitude))
                }
            }
    }

    fun getLocationFlow() = locationFlow
}