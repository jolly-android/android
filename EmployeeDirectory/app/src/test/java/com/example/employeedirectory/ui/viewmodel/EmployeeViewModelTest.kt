package com.example.employeedirectory.ui.viewmodel

import app.cash.turbine.test
import com.example.employeedirectory.data.model.Employee
import com.example.employeedirectory.data.model.EmployeeType
import com.example.employeedirectory.data.repository.EmployeeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class EmployeeViewModelTest {
    
    private lateinit var repository: EmployeeRepository
    private lateinit var viewModel: EmployeeViewModel
    private val testDispatcher = StandardTestDispatcher()
    
    private val mockEmployees = listOf(
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
            employeeType = EmployeeType.PART_TIME
        )
    )
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun `initial state is Loading`() = runTest {
        // Given
        coEvery { repository.getEmployees() } coAnswers {
            // Delay to keep it in loading state
            Result.success(mockEmployees)
        }
        
        // When
        viewModel = EmployeeViewModel(repository)
        
        // Then
        assertEquals(EmployeeUiState.Loading, viewModel.uiState.value)
    }
    
    @Test
    fun `loadEmployees updates state to Success when repository returns employees`() = runTest {
        // Given
        coEvery { repository.getEmployees() } returns Result.success(mockEmployees)
        
        // When
        viewModel = EmployeeViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()
        
        // Then
        val state = viewModel.uiState.value
        assertTrue(state is EmployeeUiState.Success)
        assertEquals(mockEmployees, (state as EmployeeUiState.Success).employees)
    }
    
    @Test
    fun `loadEmployees updates state to Empty when repository returns empty list`() = runTest {
        // Given
        coEvery { repository.getEmployees() } returns Result.success(emptyList())
        
        // When
        viewModel = EmployeeViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()
        
        // Then
        val state = viewModel.uiState.value
        assertTrue(state is EmployeeUiState.Empty)
    }
    
    @Test
    fun `loadEmployees updates state to Error when repository returns failure`() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { repository.getEmployees() } returns Result.failure(IOException(errorMessage))
        
        // When
        viewModel = EmployeeViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()
        
        // Then
        val state = viewModel.uiState.value
        assertTrue(state is EmployeeUiState.Error)
        assertEquals(errorMessage, (state as EmployeeUiState.Error).message)
    }
    
    @Test
    fun `refresh calls loadEmployees and updates state`() = runTest {
        // Given
        coEvery { repository.getEmployees() } returns Result.success(mockEmployees)
        viewModel = EmployeeViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()
        
        // When
        viewModel.refresh()
        testDispatcher.scheduler.advanceUntilIdle()
        
        // Then
        coVerify(exactly = 2) { repository.getEmployees() }
        val state = viewModel.uiState.value
        assertTrue(state is EmployeeUiState.Success)
    }
    
    @Test
    fun `uiState emits correct sequence of states on success`() = runTest {
        // Given
        coEvery { repository.getEmployees() } returns Result.success(mockEmployees)
        viewModel = EmployeeViewModel(repository)
        
        // When/Then
        viewModel.uiState.test {
            // Initial loading state
            assertEquals(EmployeeUiState.Loading, awaitItem())
            
            testDispatcher.scheduler.advanceUntilIdle()
            
            // Success state
            val successState = awaitItem()
            assertTrue(successState is EmployeeUiState.Success)
            assertEquals(mockEmployees, (successState as EmployeeUiState.Success).employees)
            
            cancelAndIgnoreRemainingEvents()
        }
    }
    
    @Test
    fun `uiState emits correct sequence of states on error`() = runTest {
        // Given
        val errorMessage = "Network timeout"
        coEvery { repository.getEmployees() } returns Result.failure(IOException(errorMessage))
        viewModel = EmployeeViewModel(repository)
        
        // When/Then
        viewModel.uiState.test {
            // Initial loading state
            assertEquals(EmployeeUiState.Loading, awaitItem())
            
            testDispatcher.scheduler.advanceUntilIdle()
            
            // Error state
            val errorState = awaitItem()
            assertTrue(errorState is EmployeeUiState.Error)
            assertEquals(errorMessage, (errorState as EmployeeUiState.Error).message)
            
            cancelAndIgnoreRemainingEvents()
        }
    }
    
    @Test
    fun `loadEmployees shows unknown error when exception message is null`() = runTest {
        // Given
        coEvery { repository.getEmployees() } returns Result.failure(RuntimeException())
        
        // When
        viewModel = EmployeeViewModel(repository)
        testDispatcher.scheduler.advanceUntilIdle()
        
        // Then
        val state = viewModel.uiState.value
        assertTrue(state is EmployeeUiState.Error)
        assertEquals("An unknown error occurred", (state as EmployeeUiState.Error).message)
    }
}

