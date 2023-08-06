package com.harshul.moviely.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.harshul.moviely.R
import com.harshul.moviely.data.models.ItemGrid
import com.harshul.moviely.data.models.Movie
import com.harshul.moviely.databinding.FragmentHomeBinding
import com.harshul.moviely.ui.adapter.HomeGridAdapter
import com.harshul.moviely.ui.adapter.MovieHorizontalAdapter
import com.harshul.moviely.ui.adapter.MovieSliderAdapter
import com.harshul.moviely.ui.adapter.OnHomeItemClick
import com.harshul.moviely.ui.adapter.OnMovieHorizontalListener
import com.harshul.moviely.ui.adapter.OnMovieSliderClickListener
import com.harshul.moviely.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment(), OnMovieSliderClickListener, OnMovieHorizontalListener,
    OnHomeItemClick {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = listOf(
            ItemGrid("Genre", R.drawable.ic_genre),
            ItemGrid("Community", R.drawable.ic_community),
            ItemGrid("Go Pro", R.drawable.ic_premium)
        )

        val gridAdapter = HomeGridAdapter(list, this)
        binding.gridView.apply {
            adapter = gridAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        val mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        mainViewModel.getTrendingMovies().observe(viewLifecycleOwner) { moviesList ->
            val adapter = MovieSliderAdapter(moviesList, this)
            binding.viewPager.adapter = adapter
        }

        binding.viewPager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        binding.viewPager.setPageTransformer(compositePageTransformer)

        mainViewModel.getAllMovies().observe(viewLifecycleOwner) { moviesList ->
            val adapterRecentlyAdded = MovieHorizontalAdapter(moviesList, this)
            binding.rvRecentlyAdded.apply {
                setHasFixedSize(true)
                adapter = adapterRecentlyAdded
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
            }
        }

    }

    override fun onClick(movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }

    override fun onHomeItemClick(position: Int) {
        if (position == 0) GenreDialog().show(parentFragmentManager, null)
        else Toast.makeText(requireContext(), "Coming Soon", Toast.LENGTH_SHORT).show()
    }

}