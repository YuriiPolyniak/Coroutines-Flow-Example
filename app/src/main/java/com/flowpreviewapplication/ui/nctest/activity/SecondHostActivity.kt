package com.flowpreviewapplication.ui.nctest.activity

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleEventObserver
import com.flowpreviewapplication.databinding.ActivitySecondHostBinding

class SecondHostActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondHostBinding

    init {
        lifecycle.addObserver(LifecycleEventObserver { source, event ->
            Log.d("TestActivity", "Second $event")
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logButton.setOnClickListener {
            val am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val tasks = am.appTasks
            Log.d("TestActivity", "TASKS: ${am.appTasks.size}")
            tasks.forEach {
                Log.d("TestActivity", "Task: ${it}, activities: ${it.taskInfo.numActivities}")
            }
        }
        binding.secondButton.setOnClickListener {
            startActivity(SecondHostActivity.newInstanceFlagged(this))
        }
        binding.thirdButton.setOnClickListener {
            startActivity(ThirdTestActivity.newInstanceFlagged(this))
        }

        Log.d("TestActivity", "onCreate Second")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        finishAffinity()
        Log.d("TestActivity", "onNewIntent Second")
    }

    companion object {

        fun newInstance(context: Context): Intent = Intent(context, SecondHostActivity::class.java)

        fun newInstanceFlagged(context: Context): Intent = Intent(context, SecondHostActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }
}