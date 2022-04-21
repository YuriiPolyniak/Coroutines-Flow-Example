package com.flowpreviewapplication.ui.pokemon.list.model

sealed class PokemonListEvent {

    object Initial : PokemonListEvent()

    data class EventButton(val id:Long = System.currentTimeMillis()) : PokemonListEvent()

    object EventFirst : PokemonListEvent()

    object EventSecond : PokemonListEvent()
    object EventThird : PokemonListEvent()

}