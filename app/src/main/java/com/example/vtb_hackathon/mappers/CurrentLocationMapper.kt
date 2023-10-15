package com.example.vtb_hackathon.mappers

import com.example.vtb_hackathon.domain.entity.CurrentLocationDomain
import com.example.vtb_hackathon.entity.CurrentLocationPresentation

fun CurrentLocationDomain.toPresentation(): CurrentLocationPresentation? =
    longitude?.let { latitude?.let { it1 -> CurrentLocationPresentation(it, it1) } }