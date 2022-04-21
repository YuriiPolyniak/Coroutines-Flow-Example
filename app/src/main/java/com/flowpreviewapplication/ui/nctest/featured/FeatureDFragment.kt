package com.flowpreviewapplication.ui.nctest.featured

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.flowpreviewapplication.R
import androidx.navigation.NavGraph
import androidx.navigation.ui.setupWithNavController
import com.flowpreviewapplication.databinding.FragmentFeatureCBinding
import com.flowpreviewapplication.databinding.FragmentFeatureDBinding

class FeatureDFragment : Fragment(R.layout.fragment_feature_d) {

    private var _binding: FragmentFeatureDBinding? = null
    private val binding: FragmentFeatureDBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFeatureDBinding.bind(view)

        val navController = findNavController()
        binding.bottomNav.setupWithNavController(navController)

        setupListeners()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupListeners() {
        binding.backButton.setOnClickListener {
        }
        binding.actionButton.setOnClickListener {
        }
    }
}