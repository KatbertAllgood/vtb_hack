package com.example.vtb_hackathon.domain.usecase.network

import com.example.vtb_hackathon.domain.entity.DepartmentDomain
import com.example.vtb_hackathon.domain.repository.NetworkRepository

class GetRecommendedOfficeByParametersUseCase(private val networkRepository: NetworkRepository) {
    operator fun invoke(departmentDomain: DepartmentDomain) =
        networkRepository.getRecommendedOfficeByParameters(departmentDomain)
}