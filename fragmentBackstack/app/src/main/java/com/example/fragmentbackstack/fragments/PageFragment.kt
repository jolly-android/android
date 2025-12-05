package com.example.fragmentbackstack.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragmentbackstack.databinding.FragmentPageBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * Page fragment used in ViewPager2.
 *
 * Demonstrates:
 * - State preservation across page swipes
 * - Lifecycle events when pages are created/destroyed
 * - Using Fragment arguments pattern
 */
class PageFragment : Fragment() {

    private var _binding: FragmentPageBinding? = null
    private val binding get() = _binding!!

    private var counter = 0
    private val lifecycleLog = StringBuilder()
    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    private val pageNumber: Int by lazy { arguments?.getInt(ARG_PAGE_NUMBER) ?: 0 }
    private val pageTitle: String by lazy { arguments?.getString(ARG_TITLE) ?: "Page" }

    companion object {
        private const val ARG_PAGE_NUMBER = "page_number"
        private const val ARG_TITLE = "title"
        private const val KEY_COUNTER = "counter"

        fun newInstance(pageNumber: Int, title: String): PageFragment {
            return PageFragment().apply {
                arguments = bundleOf(
                    ARG_PAGE_NUMBER to pageNumber,
                    ARG_TITLE to title
                )
            }
        }
    }

    private fun log(message: String) {
        val timestamp = timeFormat.format(Date())
        lifecycleLog.append("[$timestamp] $message\n")
        _binding?.txtLifecycleLog?.text = lifecycleLog.toString()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        log("onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Restore counter from saved state
        counter = savedInstanceState?.getInt(KEY_COUNTER) ?: 0
        log("onCreate (counter=$counter)")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log("onCreateView")
        _binding = FragmentPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onViewCreated")

        binding.txtPageNumber.text = "Page $pageNumber"
        binding.txtPageTitle.text = pageTitle
        binding.txtCounter.text = counter.toString()

        binding.btnIncrement.setOnClickListener {
            counter++
            binding.txtCounter.text = counter.toString()
            log("Counter incremented to $counter")
        }

        binding.txtLifecycleLog.text = lifecycleLog.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save counter so it survives page destruction
        outState.putInt(KEY_COUNTER, counter)
        log("onSaveInstanceState (counter=$counter)")
    }

    override fun onStart() {
        super.onStart()
        log("onStart")
    }

    override fun onResume() {
        super.onResume()
        log("onResume")
    }

    override fun onPause() {
        super.onPause()
        log("onPause")
    }

    override fun onStop() {
        super.onStop()
        log("onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        log("onDestroyView")
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        android.util.Log.d("PageFragment", "Page $pageNumber: onDestroy")
    }
}

