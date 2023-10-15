package com.example.vtb_hackathon.mappers

import com.example.vtb_hackathon.domain.entity.BankOfficeDomain
import com.example.vtb_hackathon.domain.entity.DepartmentDomain
import com.example.vtb_hackathon.domain.entity.ServiceDomain
import com.example.vtb_hackathon.entity.BankOfficePresentation
import com.example.vtb_hackathon.entity.DepartmentPresentation
import com.example.vtb_hackathon.entity.ServicePresentation
import com.yandex.mapkit.geometry.Point


fun DepartmentDomain.toPresentation(): DepartmentPresentation = DepartmentPresentation(
    type, services.map {
        it.toPresentation()
    }
)

fun BankOfficePresentation.toPoint(): Point = Point(
    latitude, longitude
)

fun ServiceDomain.toPresentation(): ServicePresentation = ServicePresentation(
    name, occupancy
)

fun BankOfficeDomain.toPresentation(): BankOfficePresentation = BankOfficePresentation(
    id = id,
    name = name,
    address = address,
    departments = departments.map {
        it.toPresentation()
    },
    longitude = longitude,
    latitude = latitude,
    isForLowMobility = isForLowMobility,
    occupancy = occupancy
)
