package com.example.vtb_hackathon.entity

import com.example.vtb_hackathon.domain.entity.DepartmentDomain

data class DepartmentPresentation(
    override val type: Int,
    override var services: List<ServicePresentation>
) : DepartmentDomain
