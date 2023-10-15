package com.example.vtb_hackathon.di.repositories

import com.example.vtb_hackathon.domain.repository.NetworkRepository
import com.example.vtb_hackathon.domain.usecase.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    fun providesGetAllOfficesUseCase(networkRepository: NetworkRepository) =
        GetAllOfficesUseCase(networkRepository)

    @Provides
    fun providesGetOfficeByIdUseCase(networkRepository: NetworkRepository) =
        GetOfficeByIdUseCase(networkRepository)

    @Provides
    fun providesGetRecommendedOfficeByParametersUseCase(networkRepository: NetworkRepository) =
        GetRecommendedOfficeByParametersUseCase(networkRepository)
}