package com.example.vtb_hackathon.domain.usecase.network

import com.example.vtb_hackathon.domain.repository.NetworkRepository

class GetAllOfficesUseCase(private val networkRepository: NetworkRepository) {
    operator fun invoke() = networkRepository.getAllOffices()
}