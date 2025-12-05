package com.example.fragmentbackstack.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult

/**
 * Standard DialogFragment using onCreateDialog() with AlertDialog.Builder.
 *
 * This is the simplest form of DialogFragment, ideal for:
 * - Simple confirmations (Yes/No)
 * - Single-choice selections
 * - Simple input dialogs
 *
 * Key points:
 * - Override onCreateDialog() (not onCreateView())
 * - Return an AlertDialog from the builder
 * - Use FragmentResult to communicate back to parent
 */
class StandardDialogFragment : DialogFragment() {

    companion object {
        const val REQUEST_KEY = "standard_dialog_result"
        const val RESULT_KEY = "result"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Standard DialogFragment")
            .setMessage("This dialog survives rotation!\n\nChoose an option:")
            .setPositiveButton("Confirm") { _, _ ->
                setFragmentResult(REQUEST_KEY, bundleOf(RESULT_KEY to "Confirmed ✅"))
            }
            .setNegativeButton("Cancel") { _, _ ->
                setFragmentResult(REQUEST_KEY, bundleOf(RESULT_KEY to "Cancelled ❌"))
            }
            .setNeutralButton("Maybe") { _, _ ->
                setFragmentResult(REQUEST_KEY, bundleOf(RESULT_KEY to "Maybe... 🤔"))
            }
            .create()
    }
}

