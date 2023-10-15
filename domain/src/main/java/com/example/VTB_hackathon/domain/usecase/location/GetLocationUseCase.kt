package com.example.vtb_hackathon.domain.usecase.location

import com.example.vtb_hackathon.domain.repository.LocationRepository

class GetLocationUseCase(private val locationRepository: LocationRepository) {
    suspend operator fun invoke() = locationRepository.getCurrentLocation()
}