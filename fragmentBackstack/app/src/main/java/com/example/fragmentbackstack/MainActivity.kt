package com.example.fragmentbackstack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.fragmentbackstack.databinding.ActivityMainBinding

/**
 * =====================================================================
 * MAIN ACTIVITY - Single Activity Architecture
 * =====================================================================
 *
 * ACTIVITY vs FRAGMENT - When to use which?
 *
 * USE ACTIVITY WHEN:
 * - Entry point to your app
 * - Hosting different task flows (e.g., Login vs Main flow)
 * - Multi-window support needed
 * - Deep link entry points
 *
 * USE FRAGMENT WHEN:
 * - Reusable UI components
 * - Adaptive layouts (tablet vs phone)
 * - Navigation destinations
 * - Dialogs and bottom sheets
 * - ViewPager pages
 *
 * MODERN APPROACH: Single Activity + Navigation Component
 * Benefits:
 * - Simpler navigation management
 * - Shared ViewModels across fragments
 * - Type-safe argument passing (Safe Args)
 * - Built-in deep link support
 * - Easier testing
 *
 * =====================================================================
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Navigation
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Optional: Setup ActionBar with Navigation (if using ActionBar)
        // setupActionBarWithNavController(navController)
    }

    /**
     * Handle Up navigation - delegates to NavController
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
