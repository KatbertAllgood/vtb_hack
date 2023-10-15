package com.example.vtb_hackathon.domain.repository

import com.example.vtb_hackathon.domain.entity.BankOfficeDomain
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    fun getAllOffices(): Flow<List<BankOfficeDomain>>

    fun getOfficeByCords(longitude: Double, latitude: Double): Flow<BankOfficeDomain>

    fun getOfficeById(officeId: String): Flow<BankOfficeDomain>

    suspend fun insertOffices(officesList: List<BankOfficeDomain>)

    suspend fun updateOffices(officesList: List<BankOfficeDomain>)
}