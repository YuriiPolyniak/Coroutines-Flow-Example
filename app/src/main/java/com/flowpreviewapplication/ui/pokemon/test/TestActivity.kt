package com.flowpreviewapplication.ui.pokemon.test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.flowpreviewapplication.R
import com.flowpreviewapplication.databinding.ActivityPokemonSecondBinding

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TestActivity", "onCreate B")
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.testButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TestFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onStart() {
        Log.d("TestActivity", "onStart B")
        super.onStart()
    }

    override fun onResume() {
        Log.d("TestActivity", "onResume B")
        super.onResume()
    }

    override fun onPause() {
        Log.d("TestActivity", "onPause B")
        super.onPause()
    }

    override fun onStop() {
        Log.d("TestActivity", "onStop B")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("TestActivity", "onDestroy B")
        super.onDestroy()
    }
}