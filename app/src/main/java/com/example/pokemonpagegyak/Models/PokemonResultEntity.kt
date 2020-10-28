package com.example.pokemonpagegyak.Models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonResultEntity(
 val url:String,
 val name:String
) :Parcelable{
}