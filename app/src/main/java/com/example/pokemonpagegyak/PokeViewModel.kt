package com.example.pokemonpagegyak

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokemonpagegyak.Models.PokeReszlet
import com.example.pokemonpagegyak.Repository.PokemonRetrofitRepo
import kotlinx.coroutines.Dispatchers
import java.io.IOException

class PokeViewModel @ViewModelInject constructor(private val repository: PokemonRetrofitRepo) :
    ViewModel() {

    companion object {
        private const val CURRENT_QUERY="currentQuery"
        private const val DEFAULTSEARCH = "1"
    }

    var current:String= CURRENT_QUERY
    fun searchPokemonReszletQuery(reszletid:String){
        current=reszletid
    }

    val pokelist = repository.getAllpokemon().cachedIn(viewModelScope)

    var PokemonReszletek = liveData<PokeReszlet>(Dispatchers.IO) {
        try {
        val reszletek = repository.getPokemindetailsuspent(current)
        emit(reszletek)}
        catch (e:IOException){
            Log.d("IOerror",e.toString())
        }
    }
}