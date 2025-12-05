package com.example.fragmentbackstack.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentbackstack.R
import com.example.fragmentbackstack.databinding.FragmentLifecycleBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * =====================================================================
 * FRAGMENT LIFECYCLE - Complete Overview
 * =====================================================================
 *
 * LIFECYCLE CALLBACKS (in order):
 *
 * 1. onAttach(context)
 *    - Fragment attached to Activity
 *    - Context is available
 *    - Good for: Getting references to Activity
 *
 * 2. onCreate(savedInstanceState)
 *    - Fragment instance created
 *    - NOT the view! View doesn't exist yet
 *    - Good for: Initializing non-view components, restoring state
 *
 * 3. onCreateView(inflater, container, savedInstanceState)
 *    - Create and return the view hierarchy
 *    - Don't access views yet (not attached)
 *    - Good for: Inflating layout
 *
 * 4. onViewCreated(view, savedInstanceState)
 *    - View is created and attached
 *    - SAFE to access views
 *    - Good for: Setting up UI, observers, listeners
 *
 * 5. onStart()
 *    - Fragment is visible
 *    - Good for: Starting animations, connecting to location/sensors
 *
 * 6. onResume()
 *    - Fragment is interactive (has focus)
 *    - Good for: Starting camera, resuming gameplay
 *
 * [Fragment is now fully active]
 *
 * 7. onPause()
 *    - Losing focus (another fragment/activity coming)
 *    - Good for: Pausing video, saving lightweight state
 *
 * 8. onStop()
 *    - No longer visible
 *    - Good for: Stopping animations, disconnecting sensors
 *
 * 9. onDestroyView()
 *    - View is being destroyed (but Fragment may survive!)
 *    - MUST null out view references to prevent memory leaks
 *    - Note: Fragment can stay alive (in backstack) while view is destroyed
 *
 * 10. onDestroy()
 *     - Fragment is being destroyed
 *     - Good for: Final cleanup
 *
 * 11. onDetach()
 *     - Fragment detached from Activity
 *     - Context no longer available
 *
 * =====================================================================
 *
 * COMPARISON WITH ACTIVITY:
 *
 * Activity                    Fragment
 * --------                    --------
 * [none]                      onAttach()
 * onCreate()                  onCreate()
 * [none]                      onCreateView()
 * [none]                      onViewCreated()
 * onStart()                   onStart()
 * onResume()                  onResume()
 * onPause()                   onPause()
 * onStop()                    onStop()
 * [none]                      onDestroyView()
 * onDestroy()                 onDestroy()
 * [none]                      onDetach()
 *
 * KEY DIFFERENCE: Fragment separates view lifecycle from instance lifecycle!
 *
 * =====================================================================
 */
class LifecycleFragment : Fragment() {

    private var _binding: FragmentLifecycleBinding? = null
    private val binding get() = _binding!!

    private val lifecycleLog = StringBuilder()
    private val timeFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    private fun log(message: String) {
        val timestamp = timeFormat.format(Date())
        lifecycleLog.append("[$timestamp] $message\n")
        _binding?.txtLifecycleLog?.text = lifecycleLog.toString()
    }

    // ============ LIFECYCLE CALLBACKS ============

    override fun onAttach(context: Context) {
        super.onAttach(context)
        log("🔗 onAttach() - Fragment attached to ${context.javaClass.simpleName}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val restored = if (savedInstanceState != null) "(RESTORED)" else "(fresh)"
        log("📦 onCreate() $restored - Fragment instance created, NO view yet!")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log("🎨 onCreateView() - Inflating layout...")
        _binding = FragmentLifecycleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("✅ onViewCreated() - View ready! Safe to access UI")

        binding.btnNavigateToChild.setOnClickListener {
            log("➡️ Navigating to child fragment...")
            findNavController().navigate(R.id.action_lifecycle_to_child)
        }

        binding.btnClearLog.setOnClickListener {
            lifecycleLog.clear()
            log("🧹 Log cleared")
        }

        // Update the log view
        binding.txtLifecycleLog.text = lifecycleLog.toString()
    }

    override fun onStart() {
        super.onStart()
        log("👁️ onStart() - Fragment is now VISIBLE")
    }

    override fun onResume() {
        super.onResume()
        log("▶️ onResume() - Fragment is now INTERACTIVE")
    }

    override fun onPause() {
        super.onPause()
        log("⏸️ onPause() - Fragment losing focus")
    }

    override fun onStop() {
        super.onStop()
        log("⏹️ onStop() - Fragment no longer visible")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("🗑️ onDestroyView() - View destroyed, must null binding!")
        // CRITICAL: Null out binding to prevent memory leaks
        // The fragment instance may survive (in backstack) even though view is destroyed
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        // Note: Can't log to UI anymore, binding is null
        android.util.Log.d("LifecycleFragment", "💀 onDestroy() - Fragment destroyed")
    }

    override fun onDetach() {
        super.onDetach()
        android.util.Log.d("LifecycleFragment", "🔓 onDetach() - Fragment detached from Activity")
    }
}

