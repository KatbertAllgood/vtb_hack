package com.example.vtb_hackathon.domain.repository

import com.example.vtb_hackathon.domain.entity.CurrentLocationDomain
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun getCurrentLocation():Flow<CurrentLocationDomain>
}