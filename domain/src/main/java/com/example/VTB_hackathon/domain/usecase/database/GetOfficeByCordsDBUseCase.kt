package com.example.vtb_hackathon.domain.usecase.database

import com.example.vtb_hackathon.domain.repository.DatabaseRepository

class GetOfficeByCordsDBUseCase(private val repository: DatabaseRepository) {

    operator fun invoke(longitude: Double, latitude: Double) =
        repository.getOfficeByCords(longitude = longitude, latitude = latitude)
}