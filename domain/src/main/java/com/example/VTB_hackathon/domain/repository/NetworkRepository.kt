package com.example.vtb_hackathon.domain.repository

import com.example.vtb_hackathon.domain.entity.BankOfficeDomain
import com.example.vtb_hackathon.domain.entity.DepartmentDomain
import kotlinx.coroutines.flow.Flow


interface NetworkRepository {

    fun getAllOffices(): Flow<List<BankOfficeDomain>>

    fun getOfficeById(officeId: Long): Flow<BankOfficeDomain>

    fun getRecommendedOfficeByParameters(
        departmentDomain: DepartmentDomain
    ): Flow<BankOfficeDomain>
}