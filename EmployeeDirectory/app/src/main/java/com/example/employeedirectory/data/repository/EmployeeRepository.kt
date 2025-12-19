package com.example.employeedirectory.data.repository

import com.example.employeedirectory.data.api.EmployeeApiService
import com.example.employeedirectory.data.model.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository to handle employee data operations
 */
class EmployeeRepository(
    private val apiService: EmployeeApiService
) {
    
    /**
     * Fetch employees from the API
     * @return Result containing list of employees or error
     */
    suspend fun getEmployees(): Result<List<Employee>> = withContext(Dispatchers.IO) {
        try {
            val response = apiService.getEmployees()
            Result.success(response.employees)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

