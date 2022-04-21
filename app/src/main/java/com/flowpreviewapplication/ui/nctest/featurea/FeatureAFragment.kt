package com.flowpreviewapplication.ui.nctest.featurea

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.flowpreviewapplication.R
import com.flowpreviewapplication.databinding.FragmentFeatureABinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeatureAFragment : Fragment(R.layout.fragment_feature_a) {

    //    private val viewModel: FeatureAViewModel by navGraphViewModels()
//    private val viewModel: FeatureAViewModel by koinNavGraphViewModel(
//        R.id.nav_graph,
//        parameters = { parametersOf(findNavController()) }
//    )
//    private val viewModel: FeatureAViewModel by sharedViewModel(owner = { ViewModelOwner(findNavController().getBackStackEntry(R.id.featureAFragment).viewModelStore) })
//    private val viewModel: FeatureAViewModel by stateSharedGraphViewModel(R.id.featureAFragment)
//    private val viewModel: FeatureAViewModel by sharedViewModel()
    private val viewModel: FeatureAViewModel by viewModel()

    private var _binding: FragmentFeatureABinding? = null
    private val binding: FragmentFeatureABinding
        get() = requireNotNull(_binding)

    init {
        lifecycle.addObserver(LifecycleEventObserver { source, event ->
            Log.d("TestNC", "$event A")
        })
        lifecycleScope.launchWhenStarted {
            viewModel.getStateEvents().collect { event ->
                Log.d("TestNC1", "State launchWhenStarted ${event::class.java.simpleName} A")
                binding.title.text = binding.title.text
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getStateEvents().collect { event ->
                    Log.d("TestNC1", "State repeatOnLifecycle ${event::class.java.simpleName} A")
                    binding.title.text = binding.title.text
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.getSharedEvents().collect { event ->
                Log.d("TestNC1", "Shared launchWhenStarted ${event::class.java.simpleName} A")
                binding.title.text = binding.title.text
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getSharedEvents().collect { event ->
                    Log.d("TestNC1", "Shared repeatOnLifecycle ${event::class.java.simpleName} A")
                    binding.title.text = binding.title.text
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFeatureABinding.bind(view)

        setupListeners()
        setupObservers()

        val navController = findNavController()
        binding.bottomNav.setupWithNavController(navController)

        viewModel.start()
        Log.d("TestNC1", "onViewCreated A")
    }

    private fun setupObservers() {
        viewModel.getEvents().observe(this) { event ->
            Log.d("TestNC1", "observe this ${event::class.java.simpleName} A")
        }
        viewModel.getEvents().observe(viewLifecycleOwner) { event ->
            Log.d("TestNC1", "observe view ${event::class.java.simpleName} A")
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getStateEvents().collect { event ->
                    Log.d(
                        "TestNC1",
                        "State-View repeatOnLifecycle ${event::class.java.simpleName} A"
                    )
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getSharedEvents().collect { event ->
                    Log.d(
                        "TestNC1",
                        "Shared-View repeatOnLifecycle ${event::class.java.simpleName} A"
                    )
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.whenStarted {
                viewModel.getStateEvents().collect { event ->
                    Log.d(
                        "TestNC1",
                        "State-View launchWhenStarted ${event::class.java.simpleName} A"
                    )
                }
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.whenStarted {
                viewModel.getSharedEvents().collect { event ->
                    Log.d(
                        "TestNC1",
                        "Shared-View launchWhenStarted ${event::class.java.simpleName} A"
                    )
                }
            }
        }

        viewModel.getEvents().observe(viewLifecycleOwner) { event ->
            when (event) {
                is FeatureAEvent.DisplayContent -> {
                    binding.title.text = event.content
                }
            }
        }
        val backStackEntry = findNavController().currentBackStackEntry
        backStackEntry?.savedStateHandle?.getLiveData<String>("someParameter")
            ?.observe(backStackEntry) {
                Log.d("TestNC", "Observe some parameter $it")
                backStackEntry.savedStateHandle.remove<String>("someParameter")
            }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

        Log.d("TestNC1", "onDestroyView A")
    }

    private fun setupListeners() {
        binding.backButton.setOnClickListener {
            viewModel.onGoBackClicked()

//            findNavController().navigate(
////                R.id.action_featureA_to_featureDD
//                FeatureAFragmentDirections.actionFeatureAToFeatureDD()
////                FeatureAFragmentDirections.actionFeatureAToFeatureC()
//            )
//            viewModel.onGoBackClicked()
        }
        binding.actionButton.setOnClickListener {
//            viewModel.onActionClicked()

            val navOptions: NavOptions = NavOptions.Builder()
//                .setLaunchSingleTop(true)
//                .setRestoreState(true)
//                .setPopUpTo(
//                    (findNavController().graph.startDestinationId),
//                    false,  // inclusive
//                    true
//                ) // saveState
                .build()

            findNavController().navigate(
                R.id.secondHostActivity
//                FeatureAFragmentDirections.actionFeatureAToFeatureC()
//                FeatureAFragmentDirections.actionFeatureAToFeatureB()
            )
        }
    }

    //    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        Log.d("TestNC", "onCreate A")
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        Log.d("TestNC", "onAttach A")
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        Log.d("TestNC", "onCreateView A")
//
//        return super.onCreateView(inflater, container, savedInstanceState)
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d("TestNC", "onStart A")
//    }
//
//    override fun onResume() {
//        super.onResume()
//        Log.d("TestNC", "onResume A")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        Log.d("TestNC", "onPause A")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d("TestNC", "onStop A")
//    }
//
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TestNC1", "onDestroy A")
    }
//
//    override fun onDetach() {
//        super.onDetach()
//        Log.d("TestNC", "onDetach A")
//    }

}