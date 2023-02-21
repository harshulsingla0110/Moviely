package com.harshul.moviely.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.harshul.moviely.data.models.Movie
import com.harshul.moviely.databinding.FragmentFavouritesBinding
import com.harshul.moviely.ui.adapter.FavouriteAdapter
import com.harshul.moviely.ui.adapter.OnFavClickListener
import com.harshul.moviely.ui.viewmodels.MainViewModel

class FavouritesFragment : Fragment(), OnFavClickListener {

    private lateinit var binding: FragmentFavouritesBinding
    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getFavMovies().observe(viewLifecycleOwner) { movieList ->
            if (movieList.isNullOrEmpty()) {
                binding.constraintLayoutBanner.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.constraintLayoutBanner.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                val favAdapter = FavouriteAdapter(movieList, this)
                binding.recyclerView.apply {
                    setHasFixedSize(true)
                    adapter = favAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                }
            }

        }

    }

    override fun onClick(movie: Movie) {
        val action =
            FavouritesFragmentDirections.actionFavouritesFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }

}