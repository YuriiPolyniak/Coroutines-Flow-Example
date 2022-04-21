package com.flowpreviewapplication.ui.nctest.activity

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.flowpreviewapplication.databinding.ActivityThirdTestBinding

class ThirdTestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("TestActivity", "onCreate Third")

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
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        Log.d("TestActivity", "onNewIntent Third")
    }

    companion object {

        fun newInstance(context: Context): Intent = Intent(context, ThirdTestActivity::class.java)

        fun newInstanceFlagged(context: Context): Intent =
            Intent(context, ThirdTestActivity::class.java).apply {
//                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            }
    }
}