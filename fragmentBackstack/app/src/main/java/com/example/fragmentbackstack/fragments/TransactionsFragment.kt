package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fragmentbackstack.R
import com.example.fragmentbackstack.databinding.FragmentTransactionsBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * =====================================================================
 * FRAGMENTMANAGER & FRAGMENTTRANSACTIONS
 * =====================================================================
 *
 * FRAGMENTMANAGER:
 * - Manages all fragment operations
 * - Access via:
 *   - Activity: supportFragmentManager
 *   - Fragment: parentFragmentManager (from hosting Activity/Fragment)
 *   - Fragment: childFragmentManager (for nested fragments)
 *
 * FRAGMENTTRANSACTION:
 * - Represents a set of fragment operations
 * - Created via fragmentManager.beginTransaction()
 * - Must be committed to take effect
 *
 * TRANSACTION METHODS:
 *
 * 1. add(containerId, fragment, tag)
 *    - ADDS fragment to container
 *    - Existing fragments REMAIN (stacked on top)
 *    - Use when: You want overlapping fragments
 *
 * 2. replace(containerId, fragment, tag)
 *    - REMOVES all existing fragments in container
 *    - ADDS new fragment
 *    - Use when: Standard navigation, one fragment at a time
 *
 * 3. remove(fragment)
 *    - Completely removes fragment
 *    - Full destruction lifecycle
 *
 * 4. hide(fragment)
 *    - Sets visibility to GONE
 *    - Fragment stays in memory, view intact
 *    - Calls onHiddenChanged(true)
 *    - Use when: Tab switching, preserve state
 *
 * 5. show(fragment)
 *    - Sets visibility to VISIBLE
 *    - Calls onHiddenChanged(false)
 *    - Paired with hide()
 *
 * 6. attach(fragment) / detach(fragment)
 *    - detach: Destroys view but keeps instance
 *    - attach: Recreates view
 *    - Use when: Memory optimization
 *
 * COMMIT METHODS:
 * - commit(): Async, schedules on main thread
 * - commitNow(): Synchronous, immediate
 * - commitAllowingStateLoss(): After onSaveInstanceState (risky!)
 * - executePendingTransactions(): Forces pending commits
 *
 * =====================================================================
 */
class TransactionsFragment : Fragment() {

    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!

    private val fragments = mutableListOf<ColoredFragment>()
    private val transactionLog = StringBuilder("Transaction log:\n")
    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    private fun log(message: String) {
        val timestamp = timeFormat.format(Date())
        transactionLog.append("[$timestamp] $message\n")
        _binding?.txtLog?.text = transactionLog.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButtons()
        updateState()
    }

    private fun setupButtons() {
        binding.apply {
            // ADD buttons
            btnAddRed.setOnClickListener { addFragment("Red", R.color.fragment_red) }
            btnAddBlue.setOnClickListener { addFragment("Blue", R.color.fragment_blue) }
            btnAddGreen.setOnClickListener { addFragment("Green", R.color.fragment_green) }

            // REPLACE buttons
            btnReplaceRed.setOnClickListener { replaceFragment("Red", R.color.fragment_red) }
            btnReplaceBlue.setOnClickListener { replaceFragment("Blue", R.color.fragment_blue) }
            btnReplaceGreen.setOnClickListener { replaceFragment("Green", R.color.fragment_green) }

            // HIDE/SHOW/REMOVE buttons
            btnHide.setOnClickListener { hideTopFragment() }
            btnShow.setOnClickListener { showAllFragments() }
            btnRemove.setOnClickListener { removeTopFragment() }
            btnClearAll.setOnClickListener { clearAllFragments() }
        }
    }

    private fun addFragment(name: String, colorRes: Int) {
        val fragment = ColoredFragment.newInstance(name, colorRes)
        fragments.add(fragment)

        // Using the modern commit {} extension
        childFragmentManager.commit {
            add(R.id.fragmentContainer, fragment, "fragment_$name")
        }

        log("ADD $name - now ${fragments.size} fragments")
        updateState()
    }

    private fun replaceFragment(name: String, colorRes: Int) {
        val fragment = ColoredFragment.newInstance(name, colorRes)
        fragments.clear()
        fragments.add(fragment)

        childFragmentManager.commit {
            replace(R.id.fragmentContainer, fragment, "fragment_$name")
        }

        log("REPLACE with $name - all others removed")
        updateState()
    }

    private fun hideTopFragment() {
        val visibleFragments = fragments.filter { !it.isHidden }
        if (visibleFragments.isEmpty()) {
            log("No visible fragments to hide")
            return
        }

        val topFragment = visibleFragments.last()
        childFragmentManager.commit {
            hide(topFragment)
        }

        log("HIDE ${topFragment.fragmentName} - view hidden, still in memory")
        updateState()
    }

    private fun showAllFragments() {
        val hiddenFragments = fragments.filter { it.isHidden }
        if (hiddenFragments.isEmpty()) {
            log("No hidden fragments to show")
            return
        }

        childFragmentManager.commit {
            hiddenFragments.forEach { show(it) }
        }

        log("SHOW all ${hiddenFragments.size} hidden fragments")
        updateState()
    }

    private fun removeTopFragment() {
        if (fragments.isEmpty()) {
            log("No fragments to remove")
            return
        }

        val topFragment = fragments.removeLast()
        childFragmentManager.commit {
            remove(topFragment)
        }

        log("REMOVE ${topFragment.fragmentName} - completely destroyed")
        updateState()
    }

    private fun clearAllFragments() {
        if (fragments.isEmpty()) {
            log("No fragments to clear")
            return
        }

        childFragmentManager.commit {
            fragments.forEach { remove(it) }
        }
        fragments.clear()

        log("CLEAR ALL - all fragments removed")
        updateState()
    }

    private fun updateState() {
        val visibleCount = fragments.count { !it.isHidden }
        val hiddenCount = fragments.count { it.isHidden }
        binding.txtCurrentState.text = "Current: ${fragments.size} total ($visibleCount visible, $hiddenCount hidden)"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/**
 * Simple colored fragment used for transaction demonstrations
 */
class ColoredFragment : Fragment() {

    var fragmentName: String = "Fragment"
        private set

    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_COLOR = "color"

        fun newInstance(name: String, colorRes: Int): ColoredFragment {
            return ColoredFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putInt(ARG_COLOR, colorRes)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_colored, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentName = arguments?.getString(ARG_NAME) ?: "Fragment"
        val colorRes = arguments?.getInt(ARG_COLOR) ?: R.color.fragment_blue

        view.findViewById<View>(R.id.root).setBackgroundResource(colorRes)
        view.findViewById<android.widget.TextView>(R.id.txtLabel).text = fragmentName
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        android.util.Log.d("ColoredFragment", "$fragmentName - onHiddenChanged(hidden=$hidden)")
    }
}

