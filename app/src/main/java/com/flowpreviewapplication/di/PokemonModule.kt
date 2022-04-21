package com.flowpreviewapplication.di

import androidx.navigation.NavController
import com.flowpreviewapplication.data.datasource.AppDatabase
import com.flowpreviewapplication.data.datasource.pokemon.local.PokemonsLocalDataSource
import com.flowpreviewapplication.data.datasource.pokemon.remote.PokemonsRemoteDataSource
import com.flowpreviewapplication.data.repository.pokemon.PokemonsInfoRepositoryImpl
import com.flowpreviewapplication.domain.repository.pokemon.PokemonsInfoRepository
import com.flowpreviewapplication.domain.usecase.pokemon.CatchPokemonUseCase
import com.flowpreviewapplication.domain.usecase.pokemon.GetPokemonsInfoUseCase
import com.flowpreviewapplication.domain.usecase.pokemon.IncreasePokemonLevelUseCase
import com.flowpreviewapplication.domain.usecase.pokemon.ReleaseAllPokemonsUseCase
import com.flowpreviewapplication.domain.usecase.test.TestUseCase
import com.flowpreviewapplication.ui.nctest.featurea.FeatureAViewModel
import com.flowpreviewapplication.ui.pokemon.list.PokemonsListViewModel
import com.flowpreviewapplication.ui.pokemon.test.TestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PokemonModule {

    val module = module {

        // data
        single {
            get<AppDatabase>().pokemonDao()
        }

        single {
            get<AppDatabase>().pokemonSpeciesDao()
        }

        single {
            PokemonsLocalDataSource(pokemonsDao = get(), pokemonSpeciesDao = get())
        }

        single {
            PokemonsRemoteDataSource()
        }

        // domain
        single<PokemonsInfoRepository> {
            PokemonsInfoRepositoryImpl(
                remoteDataSource = get(),
                localDataSource = get()
            )
        }
//        } binds arrayOf(PokemonsInfoRepository::class)
//        } bind PokemonsInfoRepository::class

        single { GetPokemonsInfoUseCase(pokemonsInfoRepository = get()) }

        single { CatchPokemonUseCase(pokemonsInfoRepository = get()) }

        single { IncreasePokemonLevelUseCase(pokemonsInfoRepository = get()) }

        single { ReleaseAllPokemonsUseCase(pokemonsInfoRepository = get()) }

        single { TestUseCase() }

        // presentation
        viewModel {
            PokemonsListViewModel(
                getPokemonsInfoUseCase = get(),
                catchPokemonUseCase = get(),
                increasePokemonLevelUseCase = get(),
                releaseAllPokemonsUseCase = get()
            )
        }
        viewModel { TestViewModel() }
        viewModel { FeatureAViewModel(get(), get()) }

    }

}