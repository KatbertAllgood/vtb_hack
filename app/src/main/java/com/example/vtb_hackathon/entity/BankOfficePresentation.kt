package com.example.vtb_hackathon.entity

import com.example.vtb_hackathon.domain.entity.BankOfficeDomain

data class BankOfficePresentation(
    override val id: String,
    override val name: String,
    override val address: String,
    override val departments: List<DepartmentPresentation>,
    override val longitude: Double,
    override val latitude: Double,
    override val isForLowMobility: Boolean,
    override val occupancy: Int
) : BankOfficeDomain
