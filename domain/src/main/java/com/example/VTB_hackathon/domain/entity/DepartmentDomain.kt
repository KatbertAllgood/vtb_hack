package com.example.vtb_hackathon.domain.entity

interface DepartmentDomain {
    val type:Int
    val services: List<ServiceDomain>
}