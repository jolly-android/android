package com.example.coroutines.demos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class User(val id: Int, val name: String)
data class Post(val id: Int, val userId: Int, val title: String)

class RealWorldViewModel : ViewModel() {
    private val _logs = mutableStateListOf<String>()
    val logs: List<String> = _logs
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    fun clearLogs() {
        _logs.clear()
    }
    
    private fun addLog(message: String) {
        _logs.add("${_logs.size + 1}. $message")
    }
    
    // Simulated API calls
    private suspend fun fetchUserFromApi(userId: Int): User {
        delay(1000) // Simulate network delay
        return User(userId, "User $userId")
    }
    
    private suspend fun fetchPostsFromApi(userId: Int): List<Post> {
        delay(1500) // Simulate network delay
        return listOf(
            Post(1, userId, "Post 1 by User $userId"),
            Post(2, userId, "Post 2 by User $userId")
        )
    }
    
    private suspend fun fetchUserProfile(userId: Int): String {
        delay(800)
        return "Profile data for User $userId"
    }
    
    // Demo 1: Sequential API calls
    fun demoSequentialApiCalls() {
        addLog("=== Sequential API Calls ===")
        _isLoading.value = true
        
        viewModelScope.launch {
            try {
                val startTime = System.currentTimeMillis()
                
                addLog("Fetching user...")
                val user = fetchUserFromApi(1)
                addLog("User received: ${user.name}")
                
                addLog("Fetching posts...")
                val posts = fetchPostsFromApi(user.id)
                addLog("Posts received: ${posts.size} posts")
                
                val duration = System.currentTimeMillis() - startTime
                addLog("Total time: ${duration}ms")
            } catch (e: Exception) {
                addLog("Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    // Demo 2: Parallel API calls
    fun demoParallelApiCalls() {
        addLog("=== Parallel API Calls ===")
        _isLoading.value = true
        
        viewModelScope.launch {
            try {
                val startTime = System.currentTimeMillis()
                
                addLog("Fetching user and posts in parallel...")
                
                val userDeferred = async { fetchUserFromApi(1) }
                val postsDeferred = async { fetchPostsFromApi(1) }
                
                val user = userDeferred.await()
                val posts = postsDeferred.await()
                
                addLog("User: ${user.name}")
                addLog("Posts: ${posts.size} posts")
                
                val duration = System.currentTimeMillis() - startTime
                addLog("Total time: ${duration}ms")
            } catch (e: Exception) {
                addLog("Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    // Demo 3: Error handling with retry
    private var retryAttempt = 0
    
    fun demoRetryLogic() {
        addLog("=== Retry Logic ===")
        retryAttempt = 0
        
        viewModelScope.launch {
            retry(times = 3) {
                retryAttempt++
                addLog("Attempt $retryAttempt...")
                
                if (retryAttempt < 3) {
                    throw Exception("Network error")
                }
                
                addLog("Success on attempt $retryAttempt!")
            }
        }
    }
    
    private suspend fun <T> retry(
        times: Int,
        initialDelay: Long = 500,
        factor: Double = 2.0,
        block: suspend () -> T
    ): T {
        var currentDelay = initialDelay
        repeat(times - 1) { attempt ->
            try {
                return block()
            } catch (e: Exception) {
                addLog("Failed: ${e.message}. Retrying in ${currentDelay}ms...")
                delay(currentDelay)
                currentDelay = (currentDelay * factor).toLong()
            }
        }
        return block() // last attempt
    }
    
    // Demo 4: Timeout handling
    fun demoTimeoutHandling() {
        addLog("=== Timeout Handling ===")
        
        viewModelScope.launch {
            try {
                withTimeout(2000) {
                    addLog("Starting long operation...")
                    delay(3000) // This will timeout
                    addLog("This won't be logged")
                }
            } catch (e: TimeoutCancellationException) {
                addLog("Operation timed out!")
                addLog("Falling back to cached data...")
                addLog("Cached data: [User 1, User 2]")
            }
        }
    }
    
    // Demo 5: Loading state management
    fun demoLoadingState() {
        addLog("=== Loading State Management ===")
        
        viewModelScope.launch {
            _isLoading.value = true
            addLog("Loading started...")
            
            try {
                delay(2000)
                addLog("Data loaded successfully")
            } finally {
                _isLoading.value = false
                addLog("Loading finished")
            }
        }
    }
    
    // Demo 6: Debounced search
    private val searchQuery = MutableStateFlow("")
    
    fun demoSearchDebounce() {
        addLog("=== Debounced Search ===")
        
        viewModelScope.launch {
            searchQuery
                .debounce(500)
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .collect { query ->
                    addLog("Searching for: '$query'")
                    performSearch(query)
                }
        }
        
        // Simulate typing
        viewModelScope.launch {
            val queries = listOf("k", "ko", "kot", "kotl", "kotlin")
            queries.forEach { query ->
                searchQuery.value = query
                addLog("User typed: '$query'")
                delay(200)
            }
        }
    }
    
    private suspend fun performSearch(query: String) {
        delay(500)
        addLog("Search results for '$query': [Result1, Result2, Result3]")
    }
    
    // Demo 7: Pagination
    fun demoPagination() {
        addLog("=== Pagination ===")
        
        viewModelScope.launch {
            var page = 1
            
            while (page <= 3) {
                addLog("Loading page $page...")
                val items = loadPage(page)
                addLog("Page $page loaded: ${items.size} items")
                page++
                delay(1000)
            }
            
            addLog("All pages loaded")
        }
    }
    
    private suspend fun loadPage(page: Int): List<String> {
        delay(800)
        return List(10) { "Item ${(page - 1) * 10 + it + 1}" }
    }
    
    // Demo 8: Caching with Flow
    private var cachedUsers: List<User>? = null
    
    fun demoCaching() {
        addLog("=== Caching Demo ===")
        
        viewModelScope.launch {
            repeat(3) { attempt ->
                addLog("Request ${attempt + 1}:")
                val users = getUsersWithCache()
                addLog("  Got ${users.size} users")
                delay(500)
            }
        }
    }
    
    private suspend fun getUsersWithCache(): List<User> {
        return cachedUsers ?: run {
            addLog("  Cache miss - fetching from API")
            delay(1000)
            val users = listOf(User(1, "Alice"), User(2, "Bob"))
            cachedUsers = users
            users
        }.also {
            addLog("  Cache hit - using cached data")
        }
    }
    
    // Demo 9: Multiple API calls with error handling
    fun demoMultipleCallsWithErrors() {
        addLog("=== Multiple Calls with Errors ===")
        
        viewModelScope.launch {
            supervisorScope {
                val user = async {
                    try {
                        fetchUserFromApi(1).also {
                            addLog("User fetched: ${it.name}")
                        }
                    } catch (e: Exception) {
                        addLog("User fetch failed: ${e.message}")
                        null
                    }
                }
                
                val profile = async {
                    try {
                        delay(500)
                        throw Exception("Profile API error")
                    } catch (e: Exception) {
                        addLog("Profile fetch failed: ${e.message}")
                        null
                    }
                }
                
                val posts = async {
                    try {
                        fetchPostsFromApi(1).also {
                            addLog("Posts fetched: ${it.size} posts")
                        }
                    } catch (e: Exception) {
                        addLog("Posts fetch failed: ${e.message}")
                        null
                    }
                }
                
                addLog("Waiting for all results...")
                user.await()
                profile.await()
                posts.await()
                addLog("All operations completed (some may have failed)")
            }
        }
    }
    
    // Demo 10: Polling
    fun demoPolling() {
        addLog("=== Polling Demo ===")
        
        val pollingJob = viewModelScope.launch {
            var count = 0
            while (isActive && count < 5) {
                addLog("Polling attempt ${count + 1}")
                checkForUpdates()
                count++
                delay(1000)
            }
            addLog("Polling completed")
        }
    }
    
    private suspend fun checkForUpdates() {
        delay(300)
        addLog("  Checked for updates - No new data")
    }
    
    // Demo 11: Repository pattern
    fun demoRepositoryPattern() {
        addLog("=== Repository Pattern ===")
        val repository = UserRepository()
        
        viewModelScope.launch {
            repository.getUser(1)
                .onStart { addLog("Loading user...") }
                .catch { e -> addLog("Error: ${e.message}") }
                .collect { user ->
                    addLog("User loaded: ${user.name}")
                }
        }
    }
    
    // Demo 12: Combining multiple data sources
    fun demoCombineDataSources() {
        addLog("=== Combining Data Sources ===")
        
        val localData = flow {
            addLog("Loading from local DB...")
            delay(500)
            emit(listOf("Local Item 1", "Local Item 2"))
        }
        
        val remoteData = flow {
            addLog("Loading from remote API...")
            delay(1000)
            emit(listOf("Remote Item 1", "Remote Item 2"))
        }
        
        viewModelScope.launch {
            localData.combine(remoteData) { local, remote ->
                local + remote
            }.collect { combined ->
                addLog("Combined data: ${combined.size} items")
                combined.forEach { addLog("  - $it") }
            }
        }
    }
}

// Simulated Repository
class UserRepository {
    fun getUser(userId: Int): Flow<User> = flow {
        delay(1000)
        emit(User(userId, "User from Repository"))
    }
}

@Composable
fun RealWorldDemo(viewModel: RealWorldViewModel) {
    val isLoading by viewModel.isLoading.collectAsState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Real World Demos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        if (isLoading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
        
        // Control buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoSequentialApiCalls() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Sequential", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoParallelApiCalls() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Parallel", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoRetryLogic() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Retry", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoTimeoutHandling() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Timeout", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoLoadingState() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Loading", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoSearchDebounce() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Search", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoPagination() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Pagination", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoCaching() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Caching", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoMultipleCallsWithErrors() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Errors", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoPolling() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Polling", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.demoRepositoryPattern() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Repository", style = MaterialTheme.typography.bodySmall)
                }
                Button(
                    onClick = { viewModel.demoCombineDataSources() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Combine", style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Button(
                onClick = { viewModel.clearLogs() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text("Clear Logs")
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Logs display
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                if (viewModel.logs.isEmpty()) {
                    Text(
                        text = "Tap a button to see real-world coroutine patterns",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    viewModel.logs.forEach { log ->
                        Text(
                            text = log,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

