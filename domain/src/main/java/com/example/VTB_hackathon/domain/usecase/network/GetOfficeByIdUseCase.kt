package com.example.vtb_hackathon.domain.usecase.network

import com.example.vtb_hackathon.domain.repository.NetworkRepository

class GetOfficeByIdUseCase(private val networkRepository: NetworkRepository) {

    operator fun invoke(officeId:Long) = networkRepository.getOfficeById(officeId)
}