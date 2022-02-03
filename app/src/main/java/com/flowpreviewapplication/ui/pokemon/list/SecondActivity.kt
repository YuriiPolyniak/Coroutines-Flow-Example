package com.flowpreviewapplication.ui.pokemon.list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.flowpreviewapplication.R

class SecondActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TestActivity", "onCreate B")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_second)
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