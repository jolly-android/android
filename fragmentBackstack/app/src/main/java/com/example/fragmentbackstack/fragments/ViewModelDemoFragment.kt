package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fragmentbackstack.R
import com.example.fragmentbackstack.databinding.FragmentViewmodelDemoBinding
import com.example.fragmentbackstack.viewmodels.FragmentScopedViewModel
import com.example.fragmentbackstack.viewmodels.SharedViewModel

/**
 * Demonstrates the difference between fragment-scoped and activity-scoped ViewModels.
 *
 * Fragment-scoped (by viewModels()):
 * - Each fragment instance gets its own ViewModel
 * - State is NOT shared between fragments
 * - Survives configuration changes
 * - Destroyed when fragment is destroyed
 *
 * Activity-scoped (by activityViewModels()):
 * - ALL fragments in the Activity share the SAME ViewModel instance
 * - State IS shared between fragments
 * - Lives as long as the Activity
 *
 * TRY THIS:
 * 1. Increment both counters
 * 2. Navigate to sibling fragment
 * 3. Increment counters there
 * 4. Come back - shared counter keeps its value!
 */
class ViewModelDemoFragment : Fragment() {

    private var _binding: FragmentViewmodelDemoBinding? = null
    private val binding get() = _binding!!

    // Fragment-scoped ViewModel - unique to this fragment
    private val fragmentViewModel: FragmentScopedViewModel by viewModels()

    // Activity-scoped ViewModel - shared with all fragments
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewmodelDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModels()
        setupClickListeners()
    }

    private fun observeViewModels() {
        // Observe fragment-scoped ViewModel
        fragmentViewModel.counter.observe(viewLifecycleOwner) { count ->
            binding.txtFragmentCounter.text = count.toString()
        }

        // Observe shared ViewModel
        sharedViewModel.counter.observe(viewLifecycleOwner) { count ->
            binding.txtSharedCounter.text = count.toString()
        }
    }

    private fun setupClickListeners() {
        binding.btnIncrementFragment.setOnClickListener {
            fragmentViewModel.increment()
        }

        binding.btnIncrementShared.setOnClickListener {
            sharedViewModel.increment()
        }

        binding.btnGoToSibling.setOnClickListener {
            findNavController().navigate(R.id.action_viewmodel_to_sibling)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

