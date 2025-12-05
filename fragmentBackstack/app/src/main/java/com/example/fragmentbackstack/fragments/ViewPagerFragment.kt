package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fragmentbackstack.databinding.FragmentViewpagerBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * =====================================================================
 * VIEWPAGER2 + FRAGMENTS
 * =====================================================================
 *
 * VIEWPAGER2 OVERVIEW:
 * - Modern replacement for ViewPager
 * - Built on RecyclerView
 * - Supports RTL layouts
 * - Supports vertical orientation
 * - Better fragment lifecycle handling
 *
 * FRAGMENTSTATEADAPTER:
 * - Use for fragment-based pages
 * - Manages fragment lifecycle automatically
 * - Fragments destroyed when off-screen (configurable)
 * - Must override createFragment() and getItemCount()
 *
 * CHILDFRAGMENTMANAGER:
 * - When ViewPager2 is INSIDE a Fragment, use childFragmentManager
 * - FragmentStateAdapter(fragment) uses fragment's childFragmentManager
 * - This ensures proper lifecycle handling
 * - Nested fragments are managed correctly
 *
 * KEY METHODS:
 * - setOffscreenPageLimit(): Keep more pages in memory
 * - registerOnPageChangeCallback(): Listen to page changes
 * - setUserInputEnabled(): Enable/disable swiping
 *
 * TABLAYOUTMEDIATOR:
 * - Connects TabLayout with ViewPager2
 * - Syncs tab selection with page position
 * - Allows custom tab configuration
 *
 * =====================================================================
 */
class ViewPagerFragment : Fragment() {

    private var _binding: FragmentViewpagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewpagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
    }

    private fun setupViewPager() {
        // Create adapter using childFragmentManager (because we're in a Fragment)
        val adapter = PageAdapter(this)
        binding.viewPager.adapter = adapter

        // Optional: Set offscreen page limit to keep more pages in memory
        // binding.viewPager.offscreenPageLimit = 2

        // Connect TabLayout with ViewPager2
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Basics"
                1 -> "Lifecycle"
                2 -> "State"
                3 -> "Nested"
                else -> "Page $position"
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/**
 * FragmentStateAdapter for ViewPager2
 *
 * IMPORTANT: When used inside a Fragment, pass the fragment reference
 * so it uses childFragmentManager automatically.
 */
class PageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return PageFragment.newInstance(
            pageNumber = position + 1,
            title = when (position) {
                0 -> "Fragment Basics"
                1 -> "Lifecycle Demo"
                2 -> "State Preservation"
                3 -> "Nested Fragments"
                else -> "Page ${position + 1}"
            }
        )
    }
}

