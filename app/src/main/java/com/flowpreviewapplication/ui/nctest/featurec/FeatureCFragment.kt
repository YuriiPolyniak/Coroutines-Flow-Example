package com.flowpreviewapplication.ui.nctest.featurec

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

class FeatureCFragment : Fragment(R.layout.fragment_feature_c) {

    private var _binding: FragmentFeatureCBinding? = null
    private val binding: FragmentFeatureCBinding
        get() = requireNotNull(_binding)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFeatureCBinding.bind(view)

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
//            findNavController().navigate(FeatureCFragmentDirections.restartGraph())

//            val navOptions: NavOptions = NavOptions.Builder()
//                .setLaunchSingleTop(true)
//                .setRestoreState(true)
//                .setPopUpTo(
//                    (findNavController().graph.startDestinationId),
//                    false,  // inclusive
//                    true
//                ) // saveState
//                .build()

//            findNavController().navigate(FeatureBFragmentDirections.restartGraph()){
//                resti
//            }
        }
        binding.actionButton.setOnClickListener {
//            findNavController().navigate(R.id.flowB)
            findNavController().navigate(FeatureCFragmentDirections.actionFeatureCToFeatureB())

//            findNavController().navigate(R.id.exit_nested_flow)
//            findNavController().navigate(FeatureCFragmentDirections.
//            actionFromCToA())
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("TestNC", "onSaveInstanceState С")
        outState.putString("SaveInfo", "some С info")
        super.onSaveInstanceState(outState)
    }
}