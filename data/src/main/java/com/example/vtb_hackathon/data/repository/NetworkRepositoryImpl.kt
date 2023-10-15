package com.example.vtb_hackathon.data.repository

import com.example.vtb_hackathon.data.mapper.toData
import com.example.vtb_hackathon.data.mapper.toStorageModel
import com.example.vtb_hackathon.data.network.NetworkService
import com.example.vtb_hackathon.data.utils.Constants
import com.example.vtb_hackathon.domain.entity.BankOfficeDomain
import com.example.vtb_hackathon.domain.entity.DepartmentDomain
import com.example.vtb_hackathon.domain.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkRepositoryImpl : NetworkRepository {
    private val retrofitService = NetworkService.getRetrofitService(Constants.BASE_URL)
    override fun getAllOffices(): Flow<List<BankOfficeDomain>> = flow {
        emit(retrofitService.getAllOffices().map {
            it.toStorageModel()
        })
    }

    override fun getOfficeById(officeId: Long): Flow<BankOfficeDomain> = flow {
        emit(retrofitService.getOfficeById(officeId))
    }

    override fun getRecommendedOfficeByParameters(
        departmentDomain: DepartmentDomain
    ): Flow<BankOfficeDomain> = flow {
        emit(
            retrofitService.getRecommendedOfficeByParameters(
                departmentDomain.toData()
            )
        )
    }
}