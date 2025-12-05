package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.fragmentbackstack.R
import com.example.fragmentbackstack.databinding.FragmentStackBinding

/**
 * Stack fragments used to demonstrate backstack behavior.
 * Navigation flow: BackstackDemo -> A -> B -> C
 *
 * Each fragment demonstrates different backstack operations:
 * - A: Go to B, or pop to home
 * - B: Go to C normally, or go to C with popUpTo A
 * - C: Pop to BackstackDemo clearing the stack
 */

class StackFragmentA : Fragment() {
    private var _binding: FragmentStackBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtStackName.text = "📍 Stack Fragment A"
        binding.btnNext.text = "Go to B (normal)"
        binding.btnNextWithPop.isVisible = false
        binding.btnPopToHome.text = "Pop to Home"

        binding.txtExplanation.text = """
            You're at Stack A.
            
            • "Go to B" adds B to backstack
            • "Pop to Home" clears entire stack
            
            Current backstack: Home → BackstackDemo → A
        """.trimIndent()

        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_stackA_to_stackB)
        }

        binding.btnPopToHome.setOnClickListener {
            // Pop all the way to home, but don't pop home itself
            findNavController().popBackStack(R.id.homeFragment, inclusive = false)
        }

        updateBackstackVisualization()
    }

    private fun updateBackstackVisualization() {
        val entries = findNavController().currentBackStack.value
        val visualization = entries.mapNotNull { it.destination.label?.toString() }
            .joinToString("\n") { "│ $it" }
        binding.txtBackstackVisualization.text = "Backstack:\n$visualization"
        binding.txtBackstackInfo.text = "Backstack depth: ${entries.size - 1}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class StackFragmentB : Fragment() {
    private var _binding: FragmentStackBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtStackName.text = "📍 Stack Fragment B"
        binding.btnNext.text = "Go to C (normal)"
        binding.btnNextWithPop.text = "Go to C (popUpTo A, inclusive)"
        binding.btnNextWithPop.isVisible = true
        binding.btnPopToHome.text = "Pop to Home"

        binding.txtExplanation.text = """
            You're at Stack B.
            
            • "Go to C (normal)" keeps A and B in stack
            • "Go to C (popUpTo A)" removes A from stack!
              This means pressing back from C goes to BackstackDemo
            
            popUpTo + inclusive is useful when you don't want
            users to return to intermediate screens.
        """.trimIndent()

        // Normal navigation to C
        binding.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_stackB_to_stackC)
        }

        // Navigate to C with popUpTo A (inclusive)
        binding.btnNextWithPop.setOnClickListener {
            // This will:
            // 1. Pop everything up to and including A
            // 2. Then navigate to C
            // Result: Home → BackstackDemo → C
            findNavController().navigate(R.id.action_stackB_to_stackC_popA)
        }

        binding.btnPopToHome.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment, inclusive = false)
        }

        updateBackstackVisualization()
    }

    private fun updateBackstackVisualization() {
        val entries = findNavController().currentBackStack.value
        val visualization = entries.mapNotNull { it.destination.label?.toString() }
            .joinToString("\n") { "│ $it" }
        binding.txtBackstackVisualization.text = "Backstack:\n$visualization"
        binding.txtBackstackInfo.text = "Backstack depth: ${entries.size - 1}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class StackFragmentC : Fragment() {
    private var _binding: FragmentStackBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentStackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.txtStackName.text = "📍 Stack Fragment C"
        binding.btnNext.text = "Pop to BackstackDemo"
        binding.btnNextWithPop.isVisible = false
        binding.btnPopToHome.text = "Pop to Home"

        binding.txtExplanation.text = """
            You're at Stack C.
            
            Check the backstack below - it depends on how you got here!
            
            If you came via "normal" route:
            Home → BackstackDemo → A → B → C
            
            If you came via "popUpTo A" route:
            Home → BackstackDemo → C
            
            This demonstrates how popUpTo shapes navigation!
        """.trimIndent()

        binding.btnNext.setOnClickListener {
            // Pop to backstack demo
            findNavController().popBackStack(R.id.backstackDemoFragment, inclusive = false)
        }

        binding.btnPopToHome.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment, inclusive = false)
        }

        updateBackstackVisualization()
    }

    private fun updateBackstackVisualization() {
        val entries = findNavController().currentBackStack.value
        val visualization = entries.mapNotNull { it.destination.label?.toString() }
            .joinToString("\n") { "│ $it" }
        binding.txtBackstackVisualization.text = "Backstack:\n$visualization"
        binding.txtBackstackInfo.text = "Backstack depth: ${entries.size - 1}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

