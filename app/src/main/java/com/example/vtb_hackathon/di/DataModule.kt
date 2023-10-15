package com.example.vtb_hackathon.di

import android.content.Context
import com.example.vtb_hackathon.data.repository.DatabaseRepositoryImpl
import com.example.vtb_hackathon.data.repository.LocationRepositoryImpl
import com.example.vtb_hackathon.data.repository.NetworkRepositoryImpl
import com.example.vtb_hackathon.data.repository.SharedPreferencesRepositoryImpl
import com.example.vtb_hackathon.domain.repository.DatabaseRepository
import com.example.vtb_hackathon.domain.repository.LocationRepository
import com.example.vtb_hackathon.domain.repository.NetworkRepository
import com.example.vtb_hackathon.domain.repository.SharedPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPrefsRepository(@ApplicationContext context: Context): SharedPreferencesRepository =
        SharedPreferencesRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideDatabaseRepository(@ApplicationContext context: Context): DatabaseRepository =
        DatabaseRepositoryImpl(context)

    @Provides
    @Singleton
    fun provideNetworkRepository(): NetworkRepository = NetworkRepositoryImpl()

    @Provides
    @Singleton
    fun provideLocationRepository(@ApplicationContext context: Context): LocationRepository = LocationRepositoryImpl(context)
}