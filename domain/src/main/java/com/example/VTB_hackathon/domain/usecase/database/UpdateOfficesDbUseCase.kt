package com.example.vtb_hackathon.domain.usecase.database

import com.example.vtb_hackathon.domain.entity.BankOfficeDomain
import com.example.vtb_hackathon.domain.repository.DatabaseRepository

class UpdateOfficesDBUseCase(private val repository: DatabaseRepository) {

    suspend operator fun invoke(officeList: List<BankOfficeDomain>) =
        repository.updateOffices(officesList = officeList)
}