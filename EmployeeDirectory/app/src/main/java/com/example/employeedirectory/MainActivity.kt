package com.example.employeedirectory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.employeedirectory.data.api.ApiClient
import com.example.employeedirectory.data.repository.EmployeeRepository
import com.example.employeedirectory.ui.screens.EmployeeListScreen
import com.example.employeedirectory.ui.theme.EmployeeDirectoryTheme
import com.example.employeedirectory.ui.viewmodel.EmployeeViewModel
import com.example.employeedirectory.ui.viewmodel.EmployeeViewModelFactory

class MainActivity : ComponentActivity() {
    
    private lateinit var viewModel: EmployeeViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Initialize dependencies
        val repository = EmployeeRepository(ApiClient.employeeApiService)
        val viewModelFactory = EmployeeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[EmployeeViewModel::class.java]
        
        setContent {
            EmployeeDirectoryTheme {
                EmployeeListScreen(
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}