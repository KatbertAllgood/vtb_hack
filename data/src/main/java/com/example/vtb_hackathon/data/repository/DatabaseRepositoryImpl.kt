package com.example.vtb_hackathon.data.repository

import android.content.Context
import androidx.room.Room
import com.example.vtb_hackathon.data.database.AppDatabase
import com.example.vtb_hackathon.data.mapper.toData
import com.example.vtb_hackathon.domain.entity.BankOfficeDomain
import com.example.vtb_hackathon.domain.repository.DatabaseRepository
import kotlinx.coroutines.flow.Flow

class DatabaseRepositoryImpl(context: Context) : DatabaseRepository {
    private val db = Room.databaseBuilder(
        context, AppDatabase::class.java, "local-data-base"
    ).build()

    override fun getAllOffices(): Flow<List<BankOfficeDomain>> =
        db.officeDao().getAllOffices()


    override fun getOfficeByCords(longitude: Double, latitude: Double): Flow<BankOfficeDomain> =
        db.officeDao().getOfficeByCords(longitude = longitude, latitude = latitude)

    override fun getOfficeById(officeId: String): Flow<BankOfficeDomain> =
        db.officeDao().getOfficeById(officeId = officeId)

    override suspend fun insertOffices(officesList: List<BankOfficeDomain>) =
        db.officeDao().insertOffices(officesList = officesList.map {
            it.toData()
        })

    override suspend fun updateOffices(officesList: List<BankOfficeDomain>) =
        db.officeDao().updateOffices(officesList = officesList.map {
            it.toData()
        })
}