package com.example.vtb_hackathon.data.entity

import com.example.vtb_hackathon.domain.entity.DepartmentDomain

data class DepartmentData(
    override val type: Int,
    override val services: List<ServiceData>
) : DepartmentDomain