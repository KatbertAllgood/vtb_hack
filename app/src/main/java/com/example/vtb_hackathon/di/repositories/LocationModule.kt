package com.example.vtb_hackathon.di.repositories

import com.example.vtb_hackathon.domain.repository.LocationRepository
import com.example.vtb_hackathon.domain.usecase.location.GetLocationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class LocationModule {

    @Provides
    fun provideGetLocationUseCase(locationRepository: LocationRepository): GetLocationUseCase =
        GetLocationUseCase(locationRepository)
}