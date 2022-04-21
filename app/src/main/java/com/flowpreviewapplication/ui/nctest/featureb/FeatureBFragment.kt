package com.flowpreviewapplication.ui.nctest.featureb

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.flowpreviewapplication.R
import com.flowpreviewapplication.databinding.FragmentFeatureBBinding

class FeatureBFragment : Fragment(R.layout.fragment_feature_b) {

//    private val viewModel: FeatureAViewModel by sharedViewModel()
//    private val viewModel: FeatureAViewModel by koinNavGraphViewModel(R.id.nav_graph)

    private var _binding: FragmentFeatureBBinding? = null
    private val binding: FragmentFeatureBBinding
        get() = requireNotNull(_binding)

    private val args: FeatureBFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFeatureBBinding.bind(view)

//        FeatureBFragmentArgs.fromBundle(requireArguments())
//        val userIdSafeArgs = args.userId
//        val userIdBundle = arguments?.getString("userId")

//        viewModel.start()
//        findNavController().previousBackStackEntry?.savedStateHandle?.set("someParameter", "someValue")
        findNavController().getBackStackEntry(R.id.featureAFragment).savedStateHandle.set(
            "someParameter",
            "someValue"
        )

        val restoredInfo =
            findNavController().currentBackStackEntry?.savedStateHandle?.get<String>("SaveInfo")
//        Log.d(
//            "TestNC",
//            "onViewCreated B - restoredInfo $restoredInfo savedInstanceState: $savedInstanceState"
//        )

        val mode = args.launchMode
        Log.d("TestNC", "Launch B - Mode: ${mode}")

        findNavController().currentBackStackEntry?.savedStateHandle?.set("SaveInfo", "Random info")

        val navController = findNavController()
        binding.bottomNav.setupWithNavController(navController)


        setupListeners()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        val info = savedInstanceState?.getString("SaveInfo")
        Log.d("TestNC", "onViewStateRestored B : $info")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("TestNC", "onSaveInstanceState B")
        outState.putString("SaveInfo", "some info")
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setupListeners() {
        binding.backButton.setOnClickListener {
            findNavController().navigate(FeatureBFragmentDirections.actionFeatureBToFeatureDD())

//            findNavController().navigate(FeatureBFragmentDirections.actionFeatureBToFeatureC())
        }
        binding.actionButton.setOnClickListener {
            findNavController().navigate(FeatureBFragmentDirections.actionFeatureBToFeatureD())
//            findNavController().navigate(FeatureBFragmentDirections.actionFeatureBFragmentToFeatureAFragment())
//            findNavController().navigate(FeatureBFragmentDirections.actionSubFeatureBFragmentToSubFeatureCFragment())
        }
    }
}