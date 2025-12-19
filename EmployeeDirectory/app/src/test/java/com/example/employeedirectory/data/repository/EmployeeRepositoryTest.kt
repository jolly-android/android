package com.example.employeedirectory.data.repository

import com.example.employeedirectory.data.api.EmployeeApiService
import com.example.employeedirectory.data.model.Employee
import com.example.employeedirectory.data.model.EmployeeResponse
import com.example.employeedirectory.data.model.EmployeeType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class EmployeeRepositoryTest {
    
    private lateinit var apiService: EmployeeApiService
    private lateinit var repository: EmployeeRepository
    
    @Before
    fun setup() {
        apiService = mockk()
        repository = EmployeeRepository(apiService)
    }
    
    @Test
    fun `getEmployees returns success when API call succeeds`() = runTest {
        // Given
        val mockEmployees = listOf(
            Employee(
                id = "1",
                fullName = "John Doe",
                phoneNumber = "555-0001",
                emailAddress = "john.doe@example.com",
                biography = "Software Engineer",
                photoUrlSmall = "https://example.com/photo_small.jpg",
                photoUrlLarge = "https://example.com/photo_large.jpg",
                team = "Engineering",
                employeeType = EmployeeType.FULL_TIME
            ),
            Employee(
                id = "2",
                fullName = "Jane Smith",
                phoneNumber = "555-0002",
                emailAddress = "jane.smith@example.com",
                biography = "Product Manager",
                photoUrlSmall = "https://example.com/photo_small2.jpg",
                photoUrlLarge = "https://example.com/photo_large2.jpg",
                team = "Product",
                employeeType = EmployeeType.FULL_TIME
            )
        )
        val mockResponse = EmployeeResponse(mockEmployees)
        coEvery { apiService.getEmployees() } returns mockResponse
        
        // When
        val result = repository.getEmployees()
        
        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockEmployees, result.getOrNull())
        assertEquals(2, result.getOrNull()?.size)
    }
    
    @Test
    fun `getEmployees returns empty list when API returns empty employees`() = runTest {
        // Given
        val mockResponse = EmployeeResponse(emptyList())
        coEvery { apiService.getEmployees() } returns mockResponse
        
        // When
        val result = repository.getEmployees()
        
        // Then
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()?.isEmpty() == true)
    }
    
    @Test
    fun `getEmployees returns failure when API call throws exception`() = runTest {
        // Given
        val exception = IOException("Network error")
        coEvery { apiService.getEmployees() } throws exception
        
        // When
        val result = repository.getEmployees()
        
        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
    
    @Test
    fun `getEmployees returns failure when API throws RuntimeException`() = runTest {
        // Given
        val exception = RuntimeException("Unexpected error")
        coEvery { apiService.getEmployees() } throws exception
        
        // When
        val result = repository.getEmployees()
        
        // Then
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}

