package com.example.vtb_hackathon.di.repositories

import com.example.vtb_hackathon.domain.repository.DatabaseRepository
import com.example.vtb_hackathon.domain.usecase.database.GetAllOfficesDBUseCase
import com.example.vtb_hackathon.domain.usecase.database.GetOfficeByCordsDBUseCase
import com.example.vtb_hackathon.domain.usecase.database.GetOfficeByIdDbUseCase
import com.example.vtb_hackathon.domain.usecase.database.InsertOfficeDBUseCase
import com.example.vtb_hackathon.domain.usecase.database.UpdateOfficesDBUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DatabaseModule {

    @Provides
    fun providesGetAllOfficesUseCase(databaseRepository: DatabaseRepository) =
        GetAllOfficesDBUseCase(databaseRepository)

    @Provides
    fun providesGetOfficeByIdUseCase(databaseRepository: DatabaseRepository) =
        GetOfficeByIdDbUseCase(databaseRepository)

    @Provides
    fun providesGetOfficeByCordsUseCase(databaseRepository: DatabaseRepository) =
        GetOfficeByCordsDBUseCase(databaseRepository)

    @Provides
    fun providesInsertOfficeDBUseCase(databaseRepository: DatabaseRepository) =
        InsertOfficeDBUseCase(databaseRepository)

    @Provides
    fun providesUpdateOfficeDBUseCase(databaseRepository: DatabaseRepository) =
        UpdateOfficesDBUseCase(databaseRepository)
}