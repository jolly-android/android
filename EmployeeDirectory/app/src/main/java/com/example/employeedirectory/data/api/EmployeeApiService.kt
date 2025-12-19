package com.example.employeedirectory.data.api

import com.example.employeedirectory.data.model.EmployeeResponse
import retrofit2.http.GET

/**
 * API service interface for employee endpoints
 */
interface EmployeeApiService {
    
    @GET("employees.json")
    suspend fun getEmployees(): EmployeeResponse
    
    companion object {
        const val BASE_URL = "https://s3.amazonaws.com/sq-mobile-interview/"
    }
}

