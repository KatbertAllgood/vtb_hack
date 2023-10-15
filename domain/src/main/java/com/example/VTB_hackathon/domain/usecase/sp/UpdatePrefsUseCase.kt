package com.example.vtb_hackathon.domain.usecase.sp

import com.example.vtb_hackathon.domain.repository.SharedPreferencesRepository

class UpdatePrefsUseCase(private val repository: SharedPreferencesRepository) {

    suspend operator fun invoke(key: String, value: Any) = repository.updatePrefs(key, value)
}