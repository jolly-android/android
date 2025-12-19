package com.example.employeedirectory.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.employeedirectory.ui.components.EmptyState
import com.example.employeedirectory.ui.components.EmployeeListItem
import com.example.employeedirectory.ui.components.ErrorState
import com.example.employeedirectory.ui.components.LoadingState
import com.example.employeedirectory.ui.viewmodel.EmployeeUiState
import com.example.employeedirectory.ui.viewmodel.EmployeeViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * Main screen displaying the employee list with pull-to-refresh
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeListScreen(
    viewModel: EmployeeViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val isRefreshing = uiState is EmployeeUiState.Loading
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Employee Directory",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                actions = {
                    IconButton(onClick = { viewModel.refresh() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { viewModel.refresh() },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                when (val state = uiState) {
                    is EmployeeUiState.Loading -> {
                        if (!isRefreshing) {
                            LoadingState()
                        }
                    }
                    
                    is EmployeeUiState.Success -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(
                                items = state.employees,
                                key = { it.id }
                            ) { employee ->
                                EmployeeListItem(employee = employee)
                            }
                        }
                    }
                    
                    is EmployeeUiState.Empty -> {
                        EmptyState()
                    }
                    
                    is EmployeeUiState.Error -> {
                        ErrorState(
                            message = state.message,
                            onRetry = { viewModel.refresh() }
                        )
                    }
                }
            }
        }
    }
}

