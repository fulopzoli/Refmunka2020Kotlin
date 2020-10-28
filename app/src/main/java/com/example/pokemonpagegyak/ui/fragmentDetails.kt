package com.example.pokemonpagegyak.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokemonpagegyak.Const.Urls
import com.example.pokemonpagegyak.PokeViewModel
import com.example.pokemonpagegyak.R
import com.example.pokemonpagegyak.databinding.ReszletFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class fragmentDetails : Fragment(R.layout.reszlet_fragment) {
    private val args by navArgs<fragmentDetailsArgs>()
    private val viewModel by viewModels<PokeViewModel>()
    private var _binding:ReszletFragmentBinding?=null
    private val binding get() =_binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val id=args.ids
        _binding=ReszletFragmentBinding.bind(view)

        viewModel.searchPokemonReszletQuery(id)
        viewModel.PokemonReszletek.observe(viewLifecycleOwner){
            binding.apply {

                Glide.with(this@fragmentDetails).load(Urls.BASEPICURL+id+".png")
                    .listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            DetailProgress.isVisible=false
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            DetailProgress.isVisible=false
                            PokemonreszletTextView.isVisible=true
                            imageView.isVisible=true
                            Pokemonleiras.isVisible=true
                            return false
                        }

                    }).into(imageView)
            if (it.flavorTextEntries!=null){
                var text : String=""

                for(i in 0..it.flavorTextEntries.size-1){
                    if (it.flavorTextEntries.get(i).language.name.equals("en")){
                        val char=it.flavorTextEntries.get(i).version.name.toCharArray()
                        char[0]=char[0].toUpperCase()
                        text+="\n"+String(char)+" : "+"\n"+it.flavorTextEntries.get(i).flavorText+"\n"
                    }

                }
                Pokemonleiras.setText(text)

                val nev=it.name.toCharArray()
                nev[0]=nev[0].toUpperCase()
                PokemonreszletTextView.setText(String(nev))

            }

            }

        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}