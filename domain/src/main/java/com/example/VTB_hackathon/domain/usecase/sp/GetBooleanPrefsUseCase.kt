package com.example.vtb_hackathon.domain.usecase.sp

import com.example.vtb_hackathon.domain.repository.SharedPreferencesRepository

class GetBooleanPrefsUseCase(private val repository: SharedPreferencesRepository) {
    suspend operator fun invoke(key: String) = repository.getBoolPrefs(key)
}