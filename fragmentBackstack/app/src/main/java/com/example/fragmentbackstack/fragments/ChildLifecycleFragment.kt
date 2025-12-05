package com.example.fragmentbackstack.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentbackstack.databinding.FragmentChildLifecycleBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * Child fragment to demonstrate lifecycle when navigating between fragments.
 *
 * When you navigate here from LifecycleFragment:
 * - LifecycleFragment.onPause() -> onStop() (view kept if in backstack)
 * - ChildLifecycleFragment goes through full creation
 *
 * When you navigate back:
 * - ChildLifecycleFragment goes through full destruction
 * - LifecycleFragment.onStart() -> onResume() (view recreated if was destroyed)
 */
class ChildLifecycleFragment : Fragment() {

    private var _binding: FragmentChildLifecycleBinding? = null
    private val binding get() = _binding!!

    private val lifecycleLog = StringBuilder()
    private val timeFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    private fun log(message: String) {
        val timestamp = timeFormat.format(Date())
        lifecycleLog.append("[$timestamp] $message\n")
        _binding?.txtLifecycleLog?.text = lifecycleLog.toString()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        log("🔗 onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log("📦 onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log("🎨 onCreateView()")
        _binding = FragmentChildLifecycleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("✅ onViewCreated()")

        binding.btnGoBack.setOnClickListener {
            log("⬅️ Going back...")
            findNavController().popBackStack()
        }

        binding.txtLifecycleLog.text = lifecycleLog.toString()
    }

    override fun onStart() {
        super.onStart()
        log("👁️ onStart()")
    }

    override fun onResume() {
        super.onResume()
        log("▶️ onResume()")
    }

    override fun onPause() {
        super.onPause()
        log("⏸️ onPause()")
    }

    override fun onStop() {
        super.onStop()
        log("⏹️ onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("🗑️ onDestroyView()")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        android.util.Log.d("ChildLifecycle", "💀 onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        android.util.Log.d("ChildLifecycle", "🔓 onDetach()")
    }
}

