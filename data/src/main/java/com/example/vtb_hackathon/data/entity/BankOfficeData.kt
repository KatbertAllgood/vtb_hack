package com.example.vtb_hackathon.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vtb_hackathon.domain.entity.BankOfficeDomain
import com.google.gson.annotations.SerializedName

@Entity(tableName = "offices")
data class BankOfficeData(
    @PrimaryKey
    @SerializedName("_id")
    override val id: String = "",
    override val name: String = "",
    override val address: String = "",
    override val occupancy: Int = 0,
    override val latitude: Double = 0.0,
    override val longitude: Double = 0.0,
    @SerializedName("handicapped")
    override val isForLowMobility: Boolean = false,
    override val departments: List<DepartmentData> = listOf(),
) : BankOfficeDomain
