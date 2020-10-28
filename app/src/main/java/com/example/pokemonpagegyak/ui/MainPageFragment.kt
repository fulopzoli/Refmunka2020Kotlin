package com.example.pokemonpagegyak.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pokemonpagegyak.Adapter.FooterAdapter
import com.example.pokemonpagegyak.Adapter.PokePagingAdapterss
import com.example.pokemonpagegyak.PokeViewModel
import com.example.pokemonpagegyak.R
import com.example.pokemonpagegyak.databinding.FragmentMainlistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageFragment : Fragment(R.layout.fragment_mainlist), PokePagingAdapterss.clickListener {

    private val viewmodel by viewModels<PokeViewModel>()
    private var _binding: FragmentMainlistBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        _binding = FragmentMainlistBinding.bind(view)

        val listadapter = PokePagingAdapterss(this)




        viewmodel.pokelist.observe(viewLifecycleOwner) {
            listadapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        binding.apply {
            binding.RecyclerViewPoke.apply {
                setHasFixedSize(true)
                val manager=LinearLayoutManager(context)
                layoutManager = manager
                adapter = listadapter.withLoadStateHeaderAndFooter(
                    header = FooterAdapter { listadapter.retry() },
                    footer = FooterAdapter { listadapter.retry() }
                )
            }
            RetryButton.setOnClickListener {
                listadapter.retry()
            }
        }
        listadapter.addLoadStateListener { LoadState ->
            binding.apply {
                MainProgressBar.isVisible = LoadState.source.refresh is LoadState.Loading
                RecyclerViewPoke.isVisible = LoadState.source.refresh is LoadState.NotLoading
                RetryButton.isVisible = LoadState.source.refresh is LoadState.Error
                errorTextView.isVisible = LoadState.source.refresh is LoadState.Error

                if (LoadState.source.refresh is LoadState.NotLoading && LoadState.append.endOfPaginationReached && listadapter.itemCount < 1) {
                    RecyclerViewPoke.isVisible = false
                    NoResultTextView.isVisible = true
                }
                else{
                    NoResultTextView.isVisible = false
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun click(id: String) {
        val data = MainPageFragmentDirections.actionMainPageFragmentToFragmentDetails(id)
        findNavController().navigate(data)
    }

}