package com.example.fragmentbackstack.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.fragmentbackstack.R
import com.example.fragmentbackstack.databinding.DialogFullscreenBinding

/**
 * Full-screen DialogFragment.
 *
 * Key setup:
 * 1. Set style in onCreate(): setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
 * 2. Use a theme with windowIsFloating=false
 * 3. Layout fills entire screen
 *
 * Use cases:
 * - Complex forms
 * - Detail views
 * - Photo/video viewers
 * - Document editors
 * - Settings screens
 *
 * Benefits over regular Fragment:
 * - Still has dialog dismiss behavior
 * - Can be shown from any fragment
 * - Automatic back handling
 */
class FullScreenDialogFragment : DialogFragment() {

    private var _binding: DialogFullscreenBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val REQUEST_KEY = "fullscreen_dialog_result"
        const val RESULT_TITLE = "title"
        const val RESULT_DESCRIPTION = "description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set full-screen style
        setStyle(STYLE_NORMAL, R.style.Theme_FragmentBackstack_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFullscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup toolbar
        binding.toolbar.setNavigationOnClickListener {
            dismiss()
        }

        // Setup save button
        binding.btnSave.setOnClickListener {
            val title = binding.editTitle.text.toString().ifEmpty { "(no title)" }
            val description = binding.editDescription.text.toString()

            setFragmentResult(
                REQUEST_KEY,
                bundleOf(
                    RESULT_TITLE to title,
                    RESULT_DESCRIPTION to description
                )
            )
            dismiss()
        }
    }

    override fun onStart() {
        super.onStart()
        // Ensure dialog is full screen
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

