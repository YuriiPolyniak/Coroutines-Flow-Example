package com.flowpreviewapplication.ui.pokemon.list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.flowpreviewapplication.R
import com.flowpreviewapplication.databinding.ActivityPokemonListBinding
import com.flowpreviewapplication.domain.model.Pokemon
import com.flowpreviewapplication.ui.pokemon.list.model.PokemonsListResult
import com.flowpreviewapplication.ui.pokemon.test.TestDialogFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonListBinding

    private val pokemonsListViewModel: PokemonsListViewModel by viewModel()

    private lateinit var adapter: PokemonsListAdapter
    private val listener = object : PokemonsListAdapter.Listener {

        override fun onItemClicked(item: Pokemon) {
            pokemonsListViewModel.onPokemonSelected(item)
        }

    }

    init {
        lifecycleScope.launchWhenStarted{
            pokemonsListViewModel.getSharedEvents0().collect { event ->
                Log.d("TestLifecycleEvents", "sharedEvents0: Observe: ${event.javaClass.simpleName}")
                delay(2000)
                Log.d("TestLifecycleEvents", "sharedEvents0: Observe End: ${event.javaClass.simpleName}")
            }
        }
        lifecycleScope.launchWhenStarted{
            pokemonsListViewModel.getSharedEvents0().collect { event ->
                delay(100)
                Log.d("TestLifecycleEvents", "sharedEvents0-1: Observe: ${event.javaClass.simpleName}")
//                Thread.sleep(1000)
                Log.d("TestLifecycleEvents", "sharedEvents0-1: Observe End: ${event.javaClass.simpleName}")
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                pokemonsListViewModel.getSharedEvents0().collect { event ->
                    Log.d("TestLifecycleEvents", "sharedEvents0: Observe Repeat: ${event.javaClass.simpleName}")
                }
            }
        }
//        lifecycleScope.launchWhenStarted{
//            pokemonsListViewModel.getSharedEvents1().collect { event ->
//                Log.d("TestLifecycleEvents", "sharedEvents1: Observe: ${event.javaClass.simpleName}")
//            }
//        }
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                pokemonsListViewModel.getSharedEvents1().collect { event ->
//                    Log.d("TestLifecycleEvents", "sharedEvents1: Observe Repeat: ${event.javaClass.simpleName}")
//                }
//            }
//        }
//        lifecycleScope.launchWhenStarted{
//            pokemonsListViewModel.getStateEvents().collect { event ->
//                Log.d("TestLifecycleEvents", "stateEvents: Observe: ${event.javaClass.simpleName}")
//                Thread.sleep(1000)
//                Log.d("TestLifecycleEvents", "stateEvents: Observe End: ${event.javaClass.simpleName}")
//            }
//        }
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                pokemonsListViewModel.getStateEvents().collect { event ->
//                    Log.d("TestLifecycleEvents", "stateEventsEmit: Observe Repeat: ${event.javaClass.simpleName}")
//                }
//            }
//        }

//        lifecycleScope.launchWhenStarted{
//
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//
//            }
//            whenStarted {  }
//
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TestActivity", "onCreate A")
        super.onCreate(savedInstanceState)

        binding = ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupItemsUI()
        setupListeners()
        setupObservers()

        pokemonsListViewModel.onCreate()
    }

    override fun onStart() {
        Log.d("TestActivity", "onStart A")
        super.onStart()
    }

    override fun onResume() {
        Log.d("TestActivity", "onResume A")
        super.onResume()
    }

    override fun onPause() {
        Log.d("TestActivity", "onPause A")
        super.onPause()
    }

    override fun onStop() {
        Log.d("TestActivity", "onStop A")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("TestActivity", "onDestroy A")
        super.onDestroy()
    }

    private fun setupItemsUI() {
        adapter = PokemonsListAdapter(listener = listener)
        binding.pokemonsListItemsRv.also { rv ->
            rv.adapter = adapter
        }
    }

    private fun setupListeners() {
        binding.pokemonsListCatchPokemonButton.setOnClickListener {
//            startActivity(Intent(this, TestActivity::class.java))
//            pokemonsListViewModel.onCatchPokemonAction()
            TestDialogFragment.newInstance().show(supportFragmentManager, null)
        }
        binding.pokemonsListReleaseAllPokemonsButton.setOnClickListener {
            pokemonsListViewModel.onReleaseAllPokemonsAction()
        }
    }

    private fun setupObservers() {
//        lifecycleScope.launchWhenStarted{
//
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//
//            }
//            whenStarted {  }
//
//        }
        pokemonsListViewModel.pokemonsListLiveData.observe(this,
            Observer { result ->
                handlePokemonListResult(result)
            })
        pokemonsListViewModel.getEventsLiveData().observe(this,
            Observer { event ->
                Log.d("TestLifecycleEvents", "eventsLiveData: Observe: ${event.javaClass.simpleName}")
            })
        pokemonsListViewModel.getSingleEventsLiveData().observe(this,
            Observer { event ->
                Log.d("TestLifecycleEvents", "singleEventsLiveData: Observe: ${event.javaClass.simpleName}")
            })
    }

    private fun handlePokemonListResult(result: PokemonsListResult) {
        when (result) {
            is PokemonsListResult.Loading -> {
                showOrHideProgress(visible = true)
            }
            is PokemonsListResult.Success -> {
                showOrHideProgress(visible = false)
                adapter.updateItems(result.items)
                Toast.makeText(this, R.string.pokemons_list_updated, Toast.LENGTH_SHORT)
                    .show()
            }
            is PokemonsListResult.Error -> {
                showOrHideProgress(visible = false)
                Toast.makeText(this, result.exception.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun showOrHideProgress(visible: Boolean) {
        binding.pokemonsListProgressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

}
