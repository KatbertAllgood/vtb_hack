package com.example.vtb_hackathon.data.mapper

import com.example.vtb_hackathon.data.entity.BankOfficeData
import com.example.vtb_hackathon.data.entity.BankOfficeResponse
import com.example.vtb_hackathon.data.entity.DepartmentData
import com.example.vtb_hackathon.domain.entity.BankOfficeDomain
import com.example.vtb_hackathon.domain.entity.DepartmentDomain
import com.example.vtb_hackathon.data.entity.ServiceData
import com.example.vtb_hackathon.domain.entity.ServiceDomain


fun DepartmentDomain.toData(): DepartmentData = DepartmentData(
    type, services.map {
        it.toData()
    }
)

fun ServiceDomain.toData(): ServiceData = ServiceData(
    name, occupancy
)

fun BankOfficeResponse.toStorageModel():BankOfficeData = BankOfficeData(
    id = id,
    name = name,
    address = address,
    departments = departments.map {
        it.toData()
    },
    longitude = location.coordinates[0],
    latitude = location.coordinates[1],
    isForLowMobility = isForLowMobility,
    occupancy = occupancy
)

fun BankOfficeDomain.toData(): BankOfficeData = BankOfficeData(
    id = id,
    name = name,
    address = address,
    departments = departments.map {
        it.toData()
    },
    longitude = longitude,
    latitude = latitude,
    isForLowMobility = isForLowMobility,
    occupancy = occupancy
)
