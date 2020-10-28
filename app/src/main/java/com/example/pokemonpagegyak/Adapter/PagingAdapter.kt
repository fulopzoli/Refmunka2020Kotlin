package com.example.pokemonpagegyak.Adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokemonpagegyak.Const.Urls
import com.example.pokemonpagegyak.Models.PokemonResultEntity
import com.example.pokemonpagegyak.R
import com.example.pokemonpagegyak.databinding.PokemonRecyItemBinding


class PokePagingAdapterss(private val listener: clickListener) :

    PagingDataAdapter<PokemonResultEntity, PokePagingAdapterss.myholder>(
        PokeComperator
    ) {


    //Összehasonlítás
    companion object {
        private val PokeComperator = object : DiffUtil.ItemCallback<PokemonResultEntity>() {
            override fun areItemsTheSame(
                oldItem: PokemonResultEntity,
                newItem: PokemonResultEntity
            ) = oldItem.url == newItem.url

            override fun areContentsTheSame(
                oldItem: PokemonResultEntity,
                newItem: PokemonResultEntity
            ) = oldItem == newItem

        }
    }

    //Szokásos vh
    inner class myholder(private val binding: PokemonRecyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokemonResultEntity) {
            val ids = pokemon.url.split("/").toTypedArray()
            val id=ids[6]

            binding.root.setOnClickListener {
                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION) {
                    if (id != null) {
                        listener.click(id)
                    }
                }
            }
            binding.apply {


                Glide.with(itemView).load(Urls.BASEPICURL + id + ".png")
                    .transition(DrawableTransitionOptions.withCrossFade()).error(R.drawable.poke_error)
                    .into(PokeMainPic)
//Nagybetűre csere
                val chars = pokemon.name.toCharArray()
                chars[0] = chars[0].toUpperCase()
                PokeItemNameTextView.text = String(chars)

            }
        }
    }

    override fun onBindViewHolder(holder: myholder, position: Int) {
        val currentitem = getItem(position)
        if (currentitem != null) {
            holder.bind(currentitem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myholder {
        val binding = PokemonRecyItemBinding.inflate(LayoutInflater.from(parent.context))
       // val params : ViewGroup.LayoutParams=binding.root.layoutParams
       // params.width=(parent.width*0.3).toInt()
       // binding.root.layoutParams.width=100//params.width
        return myholder(binding)
    }

    interface clickListener {
        fun click(id: String)
    }
}