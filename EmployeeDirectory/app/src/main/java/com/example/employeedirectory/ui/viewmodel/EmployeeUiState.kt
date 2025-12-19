package com.example.employeedirectory.ui.viewmodel

import com.example.employeedirectory.data.model.Employee

/**
 * UI State for the employee list screen
 */
sealed class EmployeeUiState {
    /**
     * Initial/Loading state
     */
    object Loading : EmployeeUiState()
    
    /**
     * Success state with employee data
     */
    data class Success(val employees: List<Employee>) : EmployeeUiState()
    
    /**
     * Empty state when no employees are returned
     */
    object Empty : EmployeeUiState()
    
    /**
     * Error state with error message
     */
    data class Error(val message: String) : EmployeeUiState()
}

