package com.example.pokemonpagegyak.Api

import com.example.pokemonpagegyak.Models.PokeReszlet
import com.example.pokemonpagegyak.Models.PokemonEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
@GET("pokemon/")
suspend fun Allpokemon(
    @Query("offset") offset:Int,
    @Query("limit") limit:Int
):PokemonEntity

    @GET("pokemon-species/{id}")
    suspend fun Pokemonspecies(@Path("id") id:String):PokeReszlet
}