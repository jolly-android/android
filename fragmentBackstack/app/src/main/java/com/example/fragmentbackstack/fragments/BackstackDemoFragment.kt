package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.fragmentbackstack.R
import com.example.fragmentbackstack.databinding.FragmentBackstackDemoBinding

/**
 * =====================================================================
 * BACKSTACK - Traditional vs Navigation Component
 * =====================================================================
 *
 * TRADITIONAL FRAGMENTMANAGER BACKSTACK:
 *
 * fragmentManager.beginTransaction()
 *     .replace(R.id.container, fragment)
 *     .addToBackStack("name")  // <- Key! Adds to backstack
 *     .commit()
 *
 * - addToBackStack(name): Adds transaction to backstack
 * - popBackStack(): Pops most recent entry
 * - popBackStack(name, 0): Pops to named entry (exclusive)
 * - popBackStack(name, POP_BACK_STACK_INCLUSIVE): Pops including named entry
 *
 * =====================================================================
 *
 * NAVIGATION COMPONENT BACKSTACK:
 *
 * The Navigation Component manages backstack automatically and differently:
 *
 * 1. navigate(destination)
 *    - Navigates to destination
 *    - Automatically adds current to backstack
 *    - Creates NavBackStackEntry for each destination
 *
 * 2. popBackStack()
 *    - Pops current destination
 *    - Returns to previous
 *    - Returns false if can't pop
 *
 * 3. popBackStack(destinationId, inclusive)
 *    - Pops up to specified destination
 *    - inclusive=true: also pops that destination
 *    - inclusive=false: stops before that destination
 *
 * 4. LAUNCH SINGLE TOP (launchSingleTop = true)
 *    - If destination already at top of backstack, don't add duplicate
 *    - Useful for: Bottom nav, notifications
 *    - XML: app:launchSingleTop="true"
 *    - Code: navOptions { launchSingleTop = true }
 *
 * 5. POP UP TO (popUpTo + popUpToInclusive)
 *    - popUpTo: Pop all destinations up to this one
 *    - popUpToInclusive: Also pop the specified destination
 *    - Use case: Login -> Home (clear login from stack)
 *    - XML: app:popUpTo="@id/home" app:popUpToInclusive="true"
 *
 * =====================================================================
 */
class BackstackDemoFragment : Fragment() {

    private var _binding: FragmentBackstackDemoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBackstackDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateBackstackInfo()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.apply {
            // Standard navigation - adds to backstack
            btnNavigateToA.setOnClickListener {
                findNavController().navigate(R.id.action_backstack_to_stackA)
            }

            // Single Top - won't add duplicate if already at top
            btnSingleTop.setOnClickListener {
                // Using NavOptions for launchSingleTop
                val navOptions = NavOptions.Builder()
                    .setLaunchSingleTop(true)
                    .build()
                
                findNavController().navigate(
                    R.id.backstackDemoFragment,
                    null,
                    navOptions
                )
            }

            // Pop backstack
            btnPopBackStack.setOnClickListener {
                val popped = findNavController().popBackStack()
                if (!popped) {
                    android.widget.Toast.makeText(
                        requireContext(),
                        "Can't pop - at start destination",
                        android.widget.Toast.LENGTH_SHORT
                    ).show()
                }
            }

            // Pop to home
            btnPopToHome.setOnClickListener {
                findNavController().popBackStack(R.id.homeFragment, inclusive = false)
            }
        }
    }

    private fun updateBackstackInfo() {
        val backstackCount = findNavController().currentBackStack.value.size - 1 // -1 for nav graph entry
        binding.txtBackstackCount.text = backstackCount.toString()

        // Visualize backstack
        val entries = findNavController().currentBackStack.value
        val visualization = entries.mapNotNull { entry ->
            entry.destination.label?.toString()
        }.joinToString(" → ")
        binding.txtBackstackVisualization.text = visualization.ifEmpty { "[Empty]" }
    }

    override fun onResume() {
        super.onResume()
        updateBackstackInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

