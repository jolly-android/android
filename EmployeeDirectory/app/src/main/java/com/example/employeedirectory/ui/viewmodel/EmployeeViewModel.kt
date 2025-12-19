package com.example.employeedirectory.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.employeedirectory.data.repository.EmployeeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing employee list data and UI state
 */
class EmployeeViewModel(
    private val repository: EmployeeRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<EmployeeUiState>(EmployeeUiState.Loading)
    val uiState: StateFlow<EmployeeUiState> = _uiState.asStateFlow()
    
    init {
        loadEmployees()
    }
    
    /**
     * Load employees from the repository
     * Can be called on initial load or when refreshing
     */
    fun loadEmployees() {
        _uiState.value = EmployeeUiState.Loading
        
        viewModelScope.launch {
            repository.getEmployees()
                .onSuccess { employees ->
                    _uiState.value = if (employees.isEmpty()) {
                        EmployeeUiState.Empty
                    } else {
                        EmployeeUiState.Success(employees)
                    }
                }
                .onFailure { exception ->
                    _uiState.value = EmployeeUiState.Error(
                        exception.message ?: "An unknown error occurred"
                    )
                }
        }
    }
    
    /**
     * Refresh the employee list
     */
    fun refresh() {
        loadEmployees()
    }
}

