package com.example.pokemonpagegyak.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonpagegyak.databinding.PokeStateFooterBinding

class FooterAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<FooterAdapter.LoadstateViewHolder>() {

    override fun onBindViewHolder(holder: LoadstateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadstateViewHolder {
        val binding =
            PokeStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadstateViewHolder(binding)
    }


    inner class LoadstateViewHolder(private val binding: PokeStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.FooterButtonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {

            binding.apply {
                FooterProgresBar.isVisible = loadState is LoadState.Loading
                FooterButtonRetry.isVisible = loadState !is LoadState.Loading
                LoadErrorTW.isVisible = loadState !is LoadState.Loading
            }


        }

    }


}