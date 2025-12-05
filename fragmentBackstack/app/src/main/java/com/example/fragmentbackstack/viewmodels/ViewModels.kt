package com.example.fragmentbackstack.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * =====================================================================
 * VIEWMODEL SCOPES
 * =====================================================================
 *
 * 1. FRAGMENT-SCOPED ViewModel
 *    - Created with: by viewModels()
 *    - Unique to each fragment instance
 *    - Survives configuration changes
 *    - Destroyed when fragment is destroyed
 *
 * 2. ACTIVITY-SCOPED ViewModel (Shared)
 *    - Created with: by activityViewModels()
 *    - Shared across all fragments in the Activity
 *    - Lives as long as the Activity
 *    - Use for: Cross-fragment communication
 *
 * 3. NAVIGATION GRAPH SCOPED ViewModel
 *    - Created with: by navGraphViewModels(R.id.nav_graph)
 *    - Shared across fragments in that nav graph
 *    - Cleared when nav graph is popped
 *    - Use for: Feature-module shared state
 *
 * =====================================================================
 */

/**
 * Fragment-scoped ViewModel
 * Each fragment gets its own instance
 */
class FragmentScopedViewModel : ViewModel() {
    
    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> = _counter

    fun increment() {
        _counter.value = (_counter.value ?: 0) + 1
    }
}

/**
 * Activity-scoped ViewModel (Shared)
 * All fragments using activityViewModels() get the SAME instance
 */
class SharedViewModel : ViewModel() {
    
    private val _counter = MutableLiveData(0)
    val counter: LiveData<Int> = _counter

    fun increment() {
        _counter.value = (_counter.value ?: 0) + 1
    }
}

/**
 * =====================================================================
 * STATE RESTORATION WITH SAVEDSTATEHANDLE
 * =====================================================================
 *
 * SavedStateHandle allows ViewModel to survive process death.
 *
 * Normal ViewModel:
 * - Survives configuration changes (rotation)
 * - LOST on process death
 *
 * ViewModel with SavedStateHandle:
 * - Survives configuration changes
 * - SURVIVES process death!
 *
 * Usage:
 * class MyViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
 *     // This survives process death
 *     val counter = savedStateHandle.getStateFlow("counter", 0)
 *     
 *     fun increment() {
 *         savedStateHandle["counter"] = (savedStateHandle.get<Int>("counter") ?: 0) + 1
 *     }
 * }
 *
 * =====================================================================
 */
class StateRestorationViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Regular LiveData - survives config change, LOST on process death
    private val _regularCounter = MutableLiveData(0)
    val regularCounter: LiveData<Int> = _regularCounter

    // SavedStateHandle LiveData - survives BOTH config change AND process death
    val savedStateCounter: LiveData<Int> = savedStateHandle.getLiveData("saved_counter", 0)

    fun incrementRegular() {
        _regularCounter.value = (_regularCounter.value ?: 0) + 1
    }

    fun incrementSavedState() {
        val current = savedStateHandle.get<Int>("saved_counter") ?: 0
        savedStateHandle["saved_counter"] = current + 1
    }
}

/**
 * =====================================================================
 * MVVM ARCHITECTURE VIEWMODEL
 * =====================================================================
 *
 * Best practices demonstrated:
 * - Exposes immutable state (LiveData)
 * - Sealed class for UI states
 * - Single source of truth
 * - No Android framework references
 *
 * =====================================================================
 */

// UI State sealed class
sealed class TodoUiState {
    object Idle : TodoUiState()
    object Loading : TodoUiState()
    data class Success(val todos: List<TodoItem>) : TodoUiState()
    data class Error(val message: String) : TodoUiState()
}

data class TodoItem(
    val id: Long,
    val text: String,
    val isCompleted: Boolean = false
)

class ArchitectureViewModel : ViewModel() {

    private val _uiState = MutableLiveData<TodoUiState>(TodoUiState.Idle)
    val uiState: LiveData<TodoUiState> = _uiState

    private val todoList = mutableListOf<TodoItem>()
    private var nextId = 1L

    fun addTodo(text: String) {
        if (text.isBlank()) return
        
        val newTodo = TodoItem(
            id = nextId++,
            text = text,
            isCompleted = false
        )
        todoList.add(newTodo)
        _uiState.value = TodoUiState.Success(todoList.toList())
    }

    fun toggleTodo(id: Long) {
        val index = todoList.indexOfFirst { it.id == id }
        if (index >= 0) {
            todoList[index] = todoList[index].copy(
                isCompleted = !todoList[index].isCompleted
            )
            _uiState.value = TodoUiState.Success(todoList.toList())
        }
    }

    fun deleteTodo(id: Long) {
        todoList.removeAll { it.id == id }
        _uiState.value = if (todoList.isEmpty()) {
            TodoUiState.Idle
        } else {
            TodoUiState.Success(todoList.toList())
        }
    }
}

