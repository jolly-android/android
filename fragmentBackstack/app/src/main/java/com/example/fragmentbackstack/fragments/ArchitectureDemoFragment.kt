package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentbackstack.adapters.TodoAdapter
import com.example.fragmentbackstack.databinding.FragmentArchitectureDemoBinding
import com.example.fragmentbackstack.viewmodels.ArchitectureViewModel
import com.example.fragmentbackstack.viewmodels.TodoUiState

/**
 * =====================================================================
 * MVVM ARCHITECTURE WITH FRAGMENTS - Best Practices
 * =====================================================================
 *
 * ARCHITECTURE LAYERS:
 *
 * 1. VIEW LAYER (Fragment)
 *    - Displays UI
 *    - Observes ViewModel state
 *    - Forwards user actions to ViewModel
 *    - NO business logic!
 *    - Uses ViewBinding
 *
 * 2. VIEWMODEL LAYER
 *    - Holds UI state
 *    - Exposes state via LiveData/StateFlow
 *    - Contains presentation logic
 *    - Survives configuration changes
 *    - NO Android framework references (except SavedStateHandle)
 *
 * 3. DATA LAYER (Repository)
 *    - Single source of truth
 *    - Coordinates data sources
 *    - Abstracts data operations
 *
 * 4. DATA SOURCE LAYER
 *    - Local: Room, DataStore, Files
 *    - Remote: Retrofit, Ktor
 *
 * =====================================================================
 *
 * UI STATE PATTERN:
 *
 * sealed class UiState {
 *     object Loading : UiState()
 *     data class Success(val data: T) : UiState()
 *     data class Error(val message: String) : UiState()
 * }
 *
 * Benefits:
 * - Type-safe state handling
 * - Exhaustive when expressions
 * - Clear state transitions
 * - Easy to test
 *
 * =====================================================================
 *
 * BEST PRACTICES:
 *
 * Fragment:
 * - Use ViewBinding
 * - Observe state in onViewCreated()
 * - Use viewLifecycleOwner for observers
 * - Null binding in onDestroyView()
 *
 * ViewModel:
 * - Expose immutable state (LiveData, StateFlow)
 * - Use SavedStateHandle for important state
 * - Don't hold View/Context references
 * - Single responsibility
 *
 * State:
 * - Use sealed classes
 * - Single source of truth
 * - Immutable data classes
 * - Explicit state transitions
 *
 * =====================================================================
 */
class ArchitectureDemoFragment : Fragment() {

    private var _binding: FragmentArchitectureDemoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArchitectureViewModel by viewModels()
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArchitectureDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModel()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(
            onToggle = { todo -> viewModel.toggleTodo(todo.id) },
            onDelete = { todo -> viewModel.deleteTodo(todo.id) }
        )
        binding.recyclerTodos.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = todoAdapter
        }
    }

    private fun observeViewModel() {
        // Observe UI state using viewLifecycleOwner (important!)
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            renderState(state)
        }
    }

    private fun renderState(state: TodoUiState) {
        // This is the recommended pattern: handle all states explicitly
        when (state) {
            is TodoUiState.Idle -> {
                binding.txtState.text = "State: Idle (add some todos!)"
                binding.progressBar.isVisible = false
                binding.txtError.isVisible = false
                todoAdapter.submitList(emptyList())
            }

            is TodoUiState.Loading -> {
                binding.txtState.text = "State: Loading..."
                binding.progressBar.isVisible = true
                binding.txtError.isVisible = false
            }

            is TodoUiState.Success -> {
                binding.txtState.text = "State: Success (${state.todos.size} items)"
                binding.progressBar.isVisible = false
                binding.txtError.isVisible = false
                todoAdapter.submitList(state.todos)
            }

            is TodoUiState.Error -> {
                binding.txtState.text = "State: Error"
                binding.progressBar.isVisible = false
                binding.txtError.isVisible = true
                binding.txtError.text = state.message
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnAddTodo.setOnClickListener {
            val text = binding.editNewTodo.text.toString().trim()
            if (text.isNotEmpty()) {
                viewModel.addTodo(text)
                binding.editNewTodo.text?.clear()
            } else {
                Toast.makeText(requireContext(), "Enter a todo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Important: prevent memory leaks!
    }
}

