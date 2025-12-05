package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.fragmentbackstack.databinding.FragmentStateRestorationBinding
import com.example.fragmentbackstack.viewmodels.StateRestorationViewModel

/**
 * =====================================================================
 * FRAGMENT STATE RESTORATION
 * =====================================================================
 *
 * STATE RESTORATION SCENARIOS:
 * 1. Configuration changes (rotation)
 * 2. Process death (system kills app in background)
 * 3. Fragment in backstack
 *
 * WHAT SURVIVES WHAT:
 *
 * | Storage Method          | Config Change | Process Death | App Uninstall |
 * |-------------------------|---------------|---------------|---------------|
 * | Regular Variable        | ❌ Lost       | ❌ Lost       | ❌ Lost       |
 * | ViewModel               | ✅ Survives   | ❌ Lost       | ❌ Lost       |
 * | SavedStateHandle        | ✅ Survives   | ✅ Survives   | ❌ Lost       |
 * | onSaveInstanceState     | ✅ Survives   | ✅ Survives   | ❌ Lost       |
 * | SharedPreferences       | ✅ Survives   | ✅ Survives   | ❌ Lost       |
 * | Room Database           | ✅ Survives   | ✅ Survives   | ❌ Lost       |
 * | Cloud Storage           | ✅ Survives   | ✅ Survives   | ✅ Survives   |
 *
 * VIEW STATE (automatic):
 * - Views with IDs automatically save/restore state
 * - EditText saves text content
 * - ScrollView saves scroll position
 * - RecyclerView saves scroll position (if adapter has stableIds)
 *
 * TESTING PROCESS DEATH:
 * 1. Enable "Don't keep activities" in Developer Options
 * 2. Or use: adb shell am kill <package_name>
 * 3. Or use Android Studio profiler to stop process
 *
 * =====================================================================
 */
class StateRestorationFragment : Fragment() {

    private var _binding: FragmentStateRestorationBinding? = null
    private val binding get() = _binding!!

    // ViewModel with SavedStateHandle support
    private val viewModel: StateRestorationViewModel by viewModels()

    // This variable is lost on both config change and process death
    private var bundleCounter = 0

    // Key for saving state in Bundle
    private companion object {
        const val KEY_BUNDLE_COUNTER = "bundle_counter"
        const val KEY_WAS_RESTORED = "was_restored"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Restore bundle counter from saved state
        if (savedInstanceState != null) {
            bundleCounter = savedInstanceState.getInt(KEY_BUNDLE_COUNTER, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStateRestorationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show restoration status
        val wasRestored = savedInstanceState != null
        binding.txtRestorationStatus.text = if (wasRestored) {
            "✅ State restored from saved instance"
        } else {
            "🆕 Fresh start (no restoration)"
        }

        observeViewModels()
        setupClickListeners()
        updateBundleCounterDisplay()
    }

    private fun observeViewModels() {
        // Observe regular ViewModel counter (survives config change only)
        viewModel.regularCounter.observe(viewLifecycleOwner) { count ->
            binding.txtViewModelCounter.text = count.toString()
        }

        // Observe SavedStateHandle counter (survives config change AND process death)
        viewModel.savedStateCounter.observe(viewLifecycleOwner) { count ->
            binding.txtSavedStateCounter.text = count.toString()
        }
    }

    private fun setupClickListeners() {
        binding.btnIncrementViewModel.setOnClickListener {
            viewModel.incrementRegular()
        }

        binding.btnIncrementSavedState.setOnClickListener {
            viewModel.incrementSavedState()
        }

        binding.btnIncrementBundle.setOnClickListener {
            bundleCounter++
            updateBundleCounterDisplay()
        }
    }

    private fun updateBundleCounterDisplay() {
        binding.txtBundleCounter.text = bundleCounter.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save bundle counter manually
        outState.putInt(KEY_BUNDLE_COUNTER, bundleCounter)
        outState.putBoolean(KEY_WAS_RESTORED, true)
        
        android.util.Log.d("StateRestoration", "Saved state: bundleCounter=$bundleCounter")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

