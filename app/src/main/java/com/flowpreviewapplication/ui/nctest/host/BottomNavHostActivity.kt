package com.flowpreviewapplication.ui.nctest.host

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.flowpreviewapplication.R
import com.flowpreviewapplication.databinding.ActivityBottomNavHostBinding

class BottomNavHostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            val navController = navController
            val isSameRoot =
                navController.currentDestination?.hierarchy?.any { it.id == item.itemId } ?: false
            if (!isSameRoot) {
//                navController.navigate(
//                    resId = item.itemId,
//                    args = null,
//                    navOptions = NavOptions.Builder()
//                        .setRestoreState(true)
//                        .setPopUpTo(destinationId = item.itemId, inclusive = true, saveState = true)
//                        .build()
//                )
//                true

                NavigationUI.onNavDestinationSelected(
                    item,
                    navController
                )
            } else {
                navController.navigate(
                    resId = item.itemId,
                    args = null,
                    navOptions = navOptions {
                        popUpTo(item.itemId) {
                            inclusive = true
                        }
                    }
                )
                true
            }
        }
    }
}