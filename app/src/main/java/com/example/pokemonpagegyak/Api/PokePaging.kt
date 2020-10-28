package com.example.pokemonpagegyak.Api

import android.util.Log
import androidx.paging.PagingSource
import com.example.pokemonpagegyak.Models.PokemonResultEntity
import retrofit2.HttpException
import java.io.IOException

private val OFFSETMERET = 0;

class PokePaging(
    private val pokeApi: PokeApi
) : PagingSource<Int, PokemonResultEntity>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResultEntity> {
        val position = params.key ?: OFFSETMERET
        Log.d("POZICIO",position.toString())
        print(position)
        return try {

            val response = pokeApi.Allpokemon(position, params.loadSize)
            val pokemons = response.results

            LoadResult.Page(
                data = pokemons,
                prevKey = if (position == OFFSETMERET) null else position - params.loadSize,
                nextKey = if (pokemons.isEmpty()) null else position + params.loadSize
            )

        } catch (exeption: IOException) {
            LoadResult.Error(exeption)
        } catch (exeption: HttpException) {
            LoadResult.Error(exeption)
        }
    }

}