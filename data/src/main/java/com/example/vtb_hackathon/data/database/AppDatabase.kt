package com.example.vtb_hackathon.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vtb_hackathon.data.entity.BankOfficeData

@Database(
    entities = [BankOfficeData::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun officeDao(): BankOfficeDao
}