package com.example.vtb_hackathon.domain.entity

interface BankOfficeDomain {
    val id: String
    val name: String
    val address: String
    val longitude: Double
    val latitude: Double
    val departments: List<DepartmentDomain>
    val isForLowMobility: Boolean
    val occupancy: Int
}