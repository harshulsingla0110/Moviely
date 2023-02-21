package com.harshul.moviely.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.harshul.moviely.data.models.Movie
import com.harshul.moviely.databinding.FragmentGenreBinding
import com.harshul.moviely.ui.adapter.FavouriteAdapter
import com.harshul.moviely.ui.adapter.OnFavClickListener
import com.harshul.moviely.ui.viewmodels.MainViewModel

class GenreFragment : Fragment(), OnFavClickListener {

    private lateinit var binding: FragmentGenreBinding
    private val args: GenreFragmentArgs by navArgs()
    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenreBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvGenre.text = args.category

        binding.ivBack.setOnClickListener { findNavController().popBackStack() }

        mainViewModel.getGenreMovies("%${args.category}%").observe(viewLifecycleOwner) { list ->
            val favAdapter = FavouriteAdapter(list, this)
            binding.recyclerView.apply {
                setHasFixedSize(true)
                adapter = favAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

    }

    override fun onClick(movie: Movie) {
        val action =
            GenreFragmentDirections.actionGenreFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }


}