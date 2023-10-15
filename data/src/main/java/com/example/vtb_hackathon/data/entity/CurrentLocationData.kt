package com.example.vtb_hackathon.data.entity

import com.example.vtb_hackathon.domain.entity.CurrentLocationDomain

data class CurrentLocationData(
    override val longitude: Double?,
    override val latitude: Double?
) : CurrentLocationDomain
