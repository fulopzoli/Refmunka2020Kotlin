package com.example.pokemonpagegyak.Models


import com.google.gson.annotations.SerializedName

data class Variety(
    @SerializedName("is_default")
    val isDefault: Boolean,
    val pokemon: Pokemon
)