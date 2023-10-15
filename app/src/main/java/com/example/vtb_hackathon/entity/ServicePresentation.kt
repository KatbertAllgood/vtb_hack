package com.example.vtb_hackathon.entity

import com.example.vtb_hackathon.domain.entity.ServiceDomain

data class ServicePresentation(override val name: String, override val occupancy: Int) :
    ServiceDomain
