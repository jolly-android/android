package com.example.fragmentbackstack.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.fragmentbackstack.R
import com.example.fragmentbackstack.databinding.DialogCustomBinding

/**
 * DialogFragment with custom XML layout.
 *
 * Uses onCreateView() instead of onCreateDialog() for full layout control.
 *
 * Benefits over onCreateDialog():
 * - Full control over layout
 * - Can use ViewBinding
 * - Custom animations
 * - Complex UI with RecyclerView, ViewPager, etc.
 *
 * Key points:
 * - Override onCreateView() (not onCreateDialog())
 * - Set dialog properties in onViewCreated() or onStart()
 * - Handle dismiss in button click listeners
 */
class CustomLayoutDialogFragment : DialogFragment() {

    private var _binding: DialogCustomBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val REQUEST_KEY = "custom_dialog_result"
        const val RESULT_MESSAGE = "message"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogCustomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancel.setOnClickListener {
            setFragmentResult(REQUEST_KEY, bundleOf(RESULT_MESSAGE to "Cancelled"))
            dismiss()
        }

        binding.btnSubmit.setOnClickListener {
            val message = binding.editMessage.text.toString().ifEmpty { "(empty)" }
            setFragmentResult(REQUEST_KEY, bundleOf(RESULT_MESSAGE to message))
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        // Make dialog wider
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

