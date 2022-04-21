package com.flowpreviewapplication.ui.nctest.host

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.flowpreviewapplication.R
import com.flowpreviewapplication.databinding.ActivityMainHostBinding
import com.flowpreviewapplication.ui.nctest.activity.SecondHostActivity

class MainHostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainHostBinding

    init {
        lifecycle.addObserver(LifecycleEventObserver { source, event ->
            Log.d("TestActivity", "First $event")
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setOnClickListener {
            startActivity(SecondHostActivity.newInstanceFlagged(this))
//            startActivity(SecondHostActivity.newInstance(this))
//            findNavController(R.id.nav_host_container).getViewModelStoreOwner()
//            findNavController(R.id.nav_host_container).navigate(NavGraphDirections.restartGraph())
        }

//        if (savedInstanceState == null) {
//            val navHostFragment =
//                supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
//            navHostFragment.navController.navigate(R.id.fragmentA)
//            navHostFragment.navController.navigate(R.id.fragmentC)
//        }

//        findNavController(R.id.nav_host_container).navigate(R.id.fragmentB)
//        setupNavHost()
        Log.d("TestActivity", "onCreate First")
    }

    override fun onNewIntent(intent: Intent?) {
        Log.d("TestActivity", "onNewIntent First")
        super.onNewIntent(intent)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TestActivity", "onRestart")
    }

    private fun setupNavHost() {

        val navHostFragment = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_container, navHostFragment)
            .setPrimaryNavigationFragment(navHostFragment) // app:defaultNavHost="true"
            .commit()

    }
}