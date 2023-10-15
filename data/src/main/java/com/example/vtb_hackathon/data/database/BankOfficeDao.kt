package com.example.vtb_hackathon.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vtb_hackathon.data.entity.BankOfficeData
import kotlinx.coroutines.flow.Flow

@Dao
interface BankOfficeDao {

    @Query("SELECT * FROM offices")
    fun getAllOffices(): Flow<List<BankOfficeData>>

    @Query("SELECT * FROM offices WHERE longitude IN (:longitude) AND latitude IN (:latitude)")
    fun getOfficeByCords(longitude: Double, latitude: Double): Flow<BankOfficeData>

    @Query("SELECT * FROM offices WHERE id IN (:officeId)")
    fun getOfficeById(officeId: String): Flow<BankOfficeData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffices(officesList: List<BankOfficeData>)

    @Update
    suspend fun updateOffices(officesList: List<BankOfficeData>)
}