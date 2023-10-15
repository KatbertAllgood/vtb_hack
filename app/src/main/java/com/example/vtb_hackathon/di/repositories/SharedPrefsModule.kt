package com.example.vtb_hackathon.di.repositories

import com.example.vtb_hackathon.domain.repository.SharedPreferencesRepository
import com.example.vtb_hackathon.domain.usecase.sp.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class SharedPrefsModule {
    @Provides
    fun providesGetBooleanPrefsUseCase(sharedPreferencesRepository: SharedPreferencesRepository) =
        GetBooleanPrefsUseCase(sharedPreferencesRepository)

    @Provides
    fun providesGetDoublePrefsUseCase(sharedPreferencesRepository: SharedPreferencesRepository) =
        GetDoublePrefsUseCase(sharedPreferencesRepository)

    @Provides
    fun providesGetIntPrefsUseCase(sharedPreferencesRepository: SharedPreferencesRepository) =
        GetIntPrefsUseCase(sharedPreferencesRepository)

    @Provides
    fun providesGetLongPrefsUseCase(sharedPreferencesRepository: SharedPreferencesRepository) =
        GetLongPrefsUseCase(sharedPreferencesRepository)

    @Provides
    fun providesGetStringPrefsUseCase(sharedPreferencesRepository: SharedPreferencesRepository) =
        GetStringPrefsUseCase(sharedPreferencesRepository)

    @Provides
    fun providesUpdatePrefsUseCase(sharedPreferencesRepository: SharedPreferencesRepository) =
        UpdatePrefsUseCase(sharedPreferencesRepository)
}