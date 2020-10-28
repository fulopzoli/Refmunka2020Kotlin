package com.example.pokemonpagegyak.Repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pokemonpagegyak.Api.PokeApi
import com.example.pokemonpagegyak.Api.PokePaging
import javax.inject.Inject

class PokemonRetrofitRepo @Inject constructor(private val pokeApi: PokeApi) {

    fun getAllpokemon() = Pager(
        config = PagingConfig(
            pageSize = 30,
            maxSize = 90,
            enablePlaceholders = false
        ), pagingSourceFactory = {
            PokePaging(pokeApi)
        }
    ).liveData

    suspend fun getPokemindetailsuspent(id: String) = pokeApi.Pokemonspecies(id)
}