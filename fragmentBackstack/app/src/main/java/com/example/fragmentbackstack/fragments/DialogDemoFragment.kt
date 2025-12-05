package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.fragmentbackstack.databinding.FragmentDialogDemoBinding
import com.example.fragmentbackstack.dialogs.BottomSheetDialogFragmentDemo
import com.example.fragmentbackstack.dialogs.CustomLayoutDialogFragment
import com.example.fragmentbackstack.dialogs.FullScreenDialogFragment
import com.example.fragmentbackstack.dialogs.StandardDialogFragment

/**
 * =====================================================================
 * DIALOGFRAGMENTS
 * =====================================================================
 *
 * WHY DIALOGFRAGMENT OVER ALERTDIALOG?
 * - Full Fragment lifecycle
 * - Survives configuration changes
 * - Can be shown in navigation graph
 * - Proper state restoration
 * - FragmentResult API support
 *
 * TYPES DEMONSTRATED:
 *
 * 1. Standard DialogFragment
 *    - Uses onCreateDialog() with AlertDialog.Builder
 *    - Good for simple confirmations
 *
 * 2. Custom Layout DialogFragment
 *    - Uses onCreateView() with custom XML
 *    - Full control over appearance
 *
 * 3. BottomSheetDialogFragment
 *    - Slides up from bottom
 *    - Drag to dismiss
 *    - Material Design pattern
 *
 * 4. Full-Screen DialogFragment
 *    - Fills entire screen
 *    - Good for complex forms
 *    - Uses custom theme
 *
 * SHOWING DIALOGS:
 * - show(fragmentManager, tag): Asynchronous show
 * - showNow(fragmentManager, tag): Synchronous show
 *
 * DISMISSING:
 * - dismiss(): Standard dismiss
 * - dismissAllowingStateLoss(): Safe after onSaveInstanceState
 *
 * RETURNING RESULTS:
 * Use FragmentResult API:
 * - setFragmentResult("key", bundleOf(...))
 * - setFragmentResultListener("key") { _, bundle -> }
 *
 * =====================================================================
 */
class DialogDemoFragment : Fragment() {

    private var _binding: FragmentDialogDemoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupResultListeners()
        setupClickListeners()
    }

    private fun setupResultListeners() {
        // Listen for results from all dialog types
        setFragmentResultListener(StandardDialogFragment.REQUEST_KEY) { _, bundle ->
            val result = bundle.getString(StandardDialogFragment.RESULT_KEY, "")
            binding.txtDialogResult.text = "Standard Dialog: $result"
        }

        setFragmentResultListener(CustomLayoutDialogFragment.REQUEST_KEY) { _, bundle ->
            val message = bundle.getString(CustomLayoutDialogFragment.RESULT_MESSAGE, "")
            binding.txtDialogResult.text = "Custom Dialog: $message"
        }

        setFragmentResultListener(BottomSheetDialogFragmentDemo.REQUEST_KEY) { _, bundle ->
            val action = bundle.getString(BottomSheetDialogFragmentDemo.RESULT_ACTION, "")
            binding.txtDialogResult.text = "Bottom Sheet: $action"
        }

        setFragmentResultListener(FullScreenDialogFragment.REQUEST_KEY) { _, bundle ->
            val title = bundle.getString(FullScreenDialogFragment.RESULT_TITLE, "")
            binding.txtDialogResult.text = "Full Screen: $title"
        }
    }

    private fun setupClickListeners() {
        binding.btnShowStandardDialog.setOnClickListener {
            StandardDialogFragment().show(childFragmentManager, "standard_dialog")
        }

        binding.btnShowCustomDialog.setOnClickListener {
            CustomLayoutDialogFragment().show(childFragmentManager, "custom_dialog")
        }

        binding.btnShowBottomSheet.setOnClickListener {
            BottomSheetDialogFragmentDemo().show(childFragmentManager, "bottom_sheet")
        }

        binding.btnShowFullScreenDialog.setOnClickListener {
            FullScreenDialogFragment().show(childFragmentManager, "fullscreen_dialog")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

