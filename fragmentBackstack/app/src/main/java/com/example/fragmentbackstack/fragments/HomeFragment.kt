package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentbackstack.R
import com.example.fragmentbackstack.databinding.FragmentHomeBinding

/**
 * =====================================================================
 * WHAT IS A FRAGMENT?
 * =====================================================================
 *
 * A Fragment represents a reusable portion of your app's UI. A fragment
 * defines and manages its own layout, has its own lifecycle, and can
 * handle its own input events.
 *
 * KEY CHARACTERISTICS:
 * - Must be hosted by an Activity or another Fragment
 * - Has its own lifecycle (tied to host)
 * - Can be added/removed dynamically
 * - Can be reused across different Activities
 * - Can handle configuration changes independently
 *
 * FRAGMENT CREATION PATTERNS:
 *
 * 1. Constructor with arguments (Modern, recommended):
 *    class MyFragment : Fragment(R.layout.my_fragment)
 *
 * 2. Override onCreateView (Traditional):
 *    override fun onCreateView(...) = inflater.inflate(...)
 *
 * 3. Factory method with arguments:
 *    companion object {
 *        fun newInstance(arg: String) = MyFragment().apply {
 *            arguments = bundleOf("key" to arg)
 *        }
 *    }
 *
 * =====================================================================
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.apply {
            cardLifecycle.setOnClickListener {
                findNavController().navigate(R.id.action_home_to_lifecycle)
            }

            cardTransactions.setOnClickListener {
                findNavController().navigate(R.id.action_home_to_transactions)
            }

            cardBackstack.setOnClickListener {
                findNavController().navigate(R.id.action_home_to_backstack)
            }

            cardDataPassing.setOnClickListener {
                findNavController().navigate(R.id.action_home_to_dataPassing)
            }

            cardViewModel.setOnClickListener {
                findNavController().navigate(R.id.action_home_to_viewmodel)
            }

            cardViewPager.setOnClickListener {
                findNavController().navigate(R.id.action_home_to_viewpager)
            }

            cardStateRestoration.setOnClickListener {
                findNavController().navigate(R.id.action_home_to_stateRestoration)
            }

            cardDialogs.setOnClickListener {
                findNavController().navigate(R.id.action_home_to_dialogDemo)
            }

            cardArchitecture.setOnClickListener {
                findNavController().navigate(R.id.action_home_to_architectureDemo)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Important: Prevent memory leaks!
    }
}

