package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fragmentbackstack.databinding.FragmentDataReceiverBinding

/**
 * Fragment that receives data via Safe Args and can send results back
 * using the FragmentResult API.
 */
class DataReceiverFragment : Fragment() {

    private var _binding: FragmentDataReceiverBinding? = null
    private val binding get() = _binding!!

    // Safe Args - automatically generated from nav_graph.xml arguments
    private val args: DataReceiverFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataReceiverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayReceivedData()
        setupClickListeners()
    }

    private fun displayReceivedData() {
        // Access arguments via generated args property
        binding.apply {
            txtUserName.text = args.userName
            txtUserId.text = args.userId.toString()
            txtIsPremium.text = if (args.isPremium) "✅ Yes" else "❌ No"
        }
    }

    private fun setupClickListeners() {
        binding.btnSendResultAndGoBack.setOnClickListener {
            sendResultAndGoBack()
        }
    }

    private fun sendResultAndGoBack() {
        val message = binding.editResultMessage.text.toString()

        // Send result back using FragmentResult API
        // The parent fragment (DataPassingFragment) has registered a listener for "dataResult"
        setFragmentResult(
            "dataResult",
            bundleOf(
                "message" to message,
                "timestamp" to System.currentTimeMillis()
            )
        )

        // Navigate back
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

