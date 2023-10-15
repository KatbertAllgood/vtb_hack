package com.example.vtb_hackathon.domain.repository

interface SharedPreferencesRepository {
    suspend fun updatePrefs(key: String, valueAny: Any)
    suspend fun getStringPrefs(strName: String): String
    suspend fun getBoolPrefs(strName: String): Boolean
    suspend fun getIntPrefs(strName: String): Int
    suspend fun getLongPrefs(strName: String): Long
    suspend fun getDoublePrefs(key: String): Double
}