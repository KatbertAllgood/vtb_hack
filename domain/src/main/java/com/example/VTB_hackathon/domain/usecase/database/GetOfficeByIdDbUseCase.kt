package com.example.vtb_hackathon.domain.usecase.database

import com.example.vtb_hackathon.domain.repository.DatabaseRepository

class GetOfficeByIdDbUseCase(private val repository: DatabaseRepository) {
    operator fun invoke(officeId: String) = repository.getOfficeById(officeId = officeId)
}