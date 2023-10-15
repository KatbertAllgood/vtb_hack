package com.example.vtb_hackathon.data.network

import com.example.vtb_hackathon.data.entity.BankOfficeData
import com.example.vtb_hackathon.data.entity.BankOfficeResponse
import com.example.vtb_hackathon.data.entity.DepartmentData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkApi {

    @GET("offices")
    suspend fun getAllOffices(): List<BankOfficeResponse>

    @GET("/offices/{officeId}")
    suspend fun getOfficeById(@Path("officeId") officeId: Long): BankOfficeData

    @POST("/offices/recommended")
    suspend fun getRecommendedOfficeByParameters(
        @Body departmentData: DepartmentData
    ): BankOfficeData
}