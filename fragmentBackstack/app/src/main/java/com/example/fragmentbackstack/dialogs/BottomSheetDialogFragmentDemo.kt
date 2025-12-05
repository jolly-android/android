package com.example.fragmentbackstack.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.fragmentbackstack.databinding.DialogBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * BottomSheetDialogFragment - Material Design bottom sheet.
 *
 * Key features:
 * - Slides up from bottom of screen
 * - Drag to dismiss (or swipe down)
 * - Follows Material Design guidelines
 * - Great for mobile UX
 *
 * Use cases:
 * - Action menus
 * - Share sheets
 * - Filters
 * - Settings panels
 * - Music player controls
 *
 * Behavior states:
 * - STATE_COLLAPSED: Partially visible
 * - STATE_EXPANDED: Fully expanded
 * - STATE_HALF_EXPANDED: At half height
 * - STATE_HIDDEN: Not visible
 * - STATE_DRAGGING: Being dragged
 * - STATE_SETTLING: Settling to a state
 */
class BottomSheetDialogFragmentDemo : BottomSheetDialogFragment() {

    private var _binding: DialogBottomSheetBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val REQUEST_KEY = "bottom_sheet_result"
        const val RESULT_ACTION = "action"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.optionShare.setOnClickListener {
            sendResultAndDismiss("Share 📤")
        }

        binding.optionEdit.setOnClickListener {
            sendResultAndDismiss("Edit ✏️")
        }

        binding.optionDelete.setOnClickListener {
            sendResultAndDismiss("Delete 🗑️")
        }
    }

    private fun sendResultAndDismiss(action: String) {
        setFragmentResult(REQUEST_KEY, bundleOf(RESULT_ACTION to action))
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

