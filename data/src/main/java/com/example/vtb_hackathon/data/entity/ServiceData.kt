package com.example.vtb_hackathon.data.entity

import com.example.vtb_hackathon.domain.entity.ServiceDomain

data class ServiceData(override val name: String, override val occupancy: Int) : ServiceDomain