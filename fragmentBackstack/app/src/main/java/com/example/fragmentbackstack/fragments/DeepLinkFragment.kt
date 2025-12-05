package com.example.fragmentbackstack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fragmentbackstack.R
import com.example.fragmentbackstack.databinding.FragmentDeepLinkBinding

/**
 * =====================================================================
 * DEEP LINKS + NAVIGATION COMPONENT
 * =====================================================================
 *
 * TYPES OF DEEP LINKS:
 *
 * 1. EXPLICIT DEEP LINKS
 *    - Created programmatically with NavDeepLinkBuilder
 *    - Used in: Notifications, widgets, shortcuts
 *    
 *    val pendingIntent = NavDeepLinkBuilder(context)
 *        .setGraph(R.navigation.nav_graph)
 *        .setDestination(R.id.deepLinkFragment)
 *        .setArguments(bundleOf("productId" to "123"))
 *        .createPendingIntent()
 *
 * 2. IMPLICIT DEEP LINKS
 *    - URI patterns in nav_graph.xml
 *    - System matches URIs to destinations
 *    - Defined with <deepLink app:uri="..."/>
 *
 * DEFINING IN NAV_GRAPH.XML:
 *
 * <fragment android:id="@+id/deepLinkFragment">
 *     <argument
 *         android:name="productId"
 *         app:argType="string" />
 *     <deepLink app:uri="myapp://product/{productId}" />
 *     <deepLink app:uri="https://example.com/product/{productId}" />
 * </fragment>
 *
 * MANIFEST SETUP:
 *
 * <activity android:name=".MainActivity">
 *     <nav-graph android:value="@navigation/nav_graph" />
 * </activity>
 *
 * HOW IT WORKS:
 * 1. User clicks deep link (web, notification, etc.)
 * 2. System matches URI pattern
 * 3. Navigation Component:
 *    - Extracts arguments from URI
 *    - Creates proper backstack
 *    - Navigates to destination
 *
 * TESTING:
 * adb shell am start -a android.intent.action.VIEW \
 *     -d "fragmentdemo://product/12345" \
 *     com.example.fragmentbackstack
 *
 * =====================================================================
 */
class DeepLinkFragment : Fragment() {

    private var _binding: FragmentDeepLinkBinding? = null
    private val binding get() = _binding!!

    // Arguments from deep link
    private val args: DeepLinkFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeepLinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Display the product ID from deep link argument
        binding.txtProductId.text = args.productId.ifEmpty { "(no product ID)" }

        binding.btnGoHome.setOnClickListener {
            // Navigate to home, clearing the backstack
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

