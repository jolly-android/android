package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fragmentbackstack.databinding.FragmentViewmodelSiblingBinding
import com.example.fragmentbackstack.viewmodels.FragmentScopedViewModel
import com.example.fragmentbackstack.viewmodels.SharedViewModel

/**
 * Sibling fragment to demonstrate ViewModel scoping.
 *
 * This fragment has:
 * - Its OWN FragmentScopedViewModel (different instance than ViewModelDemoFragment)
 * - The SAME SharedViewModel instance (because both use activityViewModels())
 */
class ViewModelSiblingFragment : Fragment() {

    private var _binding: FragmentViewmodelSiblingBinding? = null
    private val binding get() = _binding!!

    // This is a DIFFERENT instance than ViewModelDemoFragment's
    private val fragmentViewModel: FragmentScopedViewModel by viewModels()

    // This is the SAME instance as ViewModelDemoFragment's
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewmodelSiblingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModels()
        setupClickListeners()
    }

    private fun observeViewModels() {
        // This will start at 0 (new instance)
        fragmentViewModel.counter.observe(viewLifecycleOwner) { count ->
            binding.txtFragmentCounter.text = count.toString()
        }

        // This will show the same value as ViewModelDemoFragment!
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

        binding.btnGoBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

