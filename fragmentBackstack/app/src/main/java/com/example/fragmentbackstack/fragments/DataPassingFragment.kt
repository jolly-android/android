package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.fragmentbackstack.databinding.FragmentDataPassingBinding

/**
 * =====================================================================
 * PASSING DATA BETWEEN FRAGMENTS
 * =====================================================================
 *
 * METHOD 1: SAFE ARGS (Recommended for Navigation Component)
 *
 * Define arguments in nav_graph.xml:
 * <fragment android:id="@+id/detailFragment">
 *     <argument
 *         android:name="userId"
 *         app:argType="integer"
 *         android:defaultValue="0" />
 * </fragment>
 *
 * Send data:
 * val action = CurrentFragmentDirections
 *     .actionCurrentToDetail(userId = 123)
 * findNavController().navigate(action)
 *
 * Receive data:
 * val args: DetailFragmentArgs by navArgs()
 * val userId = args.userId
 *
 * =====================================================================
 *
 * METHOD 2: FRAGMENTRESULT API (For returning results)
 *
 * Child fragment sends result:
 * setFragmentResult("requestKey", bundleOf(
 *     "result" to "value",
 *     "data" to someData
 * ))
 *
 * Parent fragment receives result:
 * setFragmentResultListener("requestKey") { key, bundle ->
 *     val result = bundle.getString("result")
 * }
 *
 * Benefits:
 * - Lifecycle-aware (only delivers when STARTED)
 * - Works with backstack
 * - Type-safe with bundleOf
 * - No need for interfaces or callbacks
 *
 * =====================================================================
 *
 * METHOD 3: SHARED VIEWMODEL
 *
 * Activity-scoped ViewModel shared between fragments:
 *
 * // In Fragment A
 * val sharedVM: SharedViewModel by activityViewModels()
 * sharedVM.setSelectedItem(item)
 *
 * // In Fragment B
 * val sharedVM: SharedViewModel by activityViewModels()
 * sharedVM.selectedItem.observe(viewLifecycleOwner) { item -> }
 *
 * Best for: Complex shared state, continuous updates
 *
 * =====================================================================
 *
 * METHOD 4: SAVEDSTATEHANDLE IN NAVBACKSTACKENTRY
 *
 * Set data before navigating:
 * findNavController().currentBackStackEntry?.savedStateHandle?.set("key", value)
 *
 * Or for previous entry (useful for results):
 * findNavController().previousBackStackEntry?.savedStateHandle?.set("key", value)
 *
 * Receive in destination:
 * findNavController().currentBackStackEntry?.savedStateHandle
 *     ?.getLiveData<String>("key")
 *     ?.observe(viewLifecycleOwner) { value -> }
 *
 * =====================================================================
 */
class DataPassingFragment : Fragment() {

    private var _binding: FragmentDataPassingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataPassingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFragmentResultListener()
        setupClickListeners()
    }

    private fun setupFragmentResultListener() {
        // Listen for results from child fragment (DataReceiverFragment)
        setFragmentResultListener("dataResult") { requestKey, bundle ->
            val message = bundle.getString("message", "")
            val timestamp = bundle.getLong("timestamp", 0)

            binding.txtResultFromChild.text = buildString {
                append("Result from child:\n")
                append("Message: $message\n")
                append("Timestamp: $timestamp")
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnNavigateWithSafeArgs.setOnClickListener {
            navigateWithSafeArgs()
        }
    }

    private fun navigateWithSafeArgs() {
        val userName = binding.editUserName.text.toString().ifEmpty { "Guest" }
        val userId = binding.editUserId.text.toString().toIntOrNull() ?: 0
        val isPremium = binding.switchPremium.isChecked

        // Navigate using Safe Args
        // Note: Safe Args generates DataPassingFragmentDirections class
        val action = DataPassingFragmentDirections.actionDataPassingToReceiver(
            userName = userName,
            userId = userId,
            isPremium = isPremium
        )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

