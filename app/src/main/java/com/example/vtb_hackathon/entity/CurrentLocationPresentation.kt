package com.example.vtb_hackathon.entity

import com.example.vtb_hackathon.domain.entity.CurrentLocationDomain

data class CurrentLocationPresentation(
    override val longitude: Double,
    override val latitude: Double
) : CurrentLocationDomain