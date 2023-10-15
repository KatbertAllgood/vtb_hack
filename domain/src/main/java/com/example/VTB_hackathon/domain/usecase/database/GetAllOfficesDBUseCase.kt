package com.example.vtb_hackathon.domain.usecase.database

import com.example.vtb_hackathon.domain.repository.DatabaseRepository

class GetAllOfficesDBUseCase(private val repository: DatabaseRepository) {
    operator fun invoke() = repository.getAllOffices()
}