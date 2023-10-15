package com.example.vtb_hackathon.domain.usecase.sp

import com.example.vtb_hackathon.domain.repository.SharedPreferencesRepository

class GetStringPrefsUseCase(private val repository: SharedPreferencesRepository) {
    suspend operator fun invoke(key: String) = repository.getStringPrefs(key)
}