package com.example.vtb_hackathon.data.entity

import com.google.gson.annotations.SerializedName

data class BankOfficeResponse(
    @SerializedName("_id")
    val id: String = "",
    val name: String = "",
    val address: String = "",
    val occupancy: Int = 0,
    val location: LocationResponse,
    @SerializedName("handicapped")
    val isForLowMobility: Boolean = false,
    val departments: List<DepartmentData> = listOf(),
)

data class LocationResponse(
    @SerializedName("coordinates")
    val coordinates: List<Double>
)