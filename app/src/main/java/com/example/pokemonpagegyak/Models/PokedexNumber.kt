package com.example.pokemonpagegyak.Models


import com.google.gson.annotations.SerializedName

data class PokedexNumber(
    @SerializedName("entry_number")
    val entryNumber: Int,
    val pokedex: Pokedex
)