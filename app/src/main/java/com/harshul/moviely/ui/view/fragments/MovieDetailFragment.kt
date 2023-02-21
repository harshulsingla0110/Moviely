package com.harshul.moviely.ui.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.harshul.moviely.R
import com.harshul.moviely.data.models.Movie
import com.harshul.moviely.databinding.FragmentMovieDetailBinding
import com.harshul.moviely.ui.adapter.ActorAdapter
import com.harshul.moviely.ui.viewmodels.MainViewModel
import com.harshul.moviely.utils.Utils.setMotionVisibility
import kotlin.math.abs

class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieDetailFragmentArgs by navArgs()
    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(requireActivity())[MainViewModel::class.java] }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = args.movieDetail

        binding.textViewName.text = movie.title
        binding.textViewCategory.text = movie.category
        binding.textViewDuration.text = movie.duration
        binding.textViewRating.text = "⭐ ${movie.rating}"

        binding.textViewPopularity.text = abs(movie.popularity).toString()
        if (movie.popularity < 0) binding.imageViewPopularity.setImageResource(R.drawable.ic_decrease)
        else binding.imageViewPopularity.setImageResource(R.drawable.ic_increase)

        binding.tvSummary.text = movie.summary

        if (movie.user_rating >= 0) ratingDone(movie)

        Glide.with(requireContext()).load(movie.thumbnail)
            .into(binding.ivBanner)

        isFavourite(movie)

        binding.ivBack.setOnClickListener { findNavController().popBackStack() }

        binding.cvRate.setOnClickListener {
            binding.ratingBar.setMotionVisibility(if (binding.ratingBar.isVisible) View.GONE else View.VISIBLE)
        }

        binding.cvFavourite.setOnClickListener {
            movie.is_favourite = !movie.is_favourite
            isFavourite(movie)
            mainViewModel.updateMovie(movie)
        }

        binding.ratingBar.setOnRatingBarChangeListener { _, value, _ ->
            movie.user_rating = value
            ratingDone(movie)
            mainViewModel.updateMovie(movie)
        }

        binding.cvTrailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movie.trailer)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                setPackage("com.google.android.youtube")
            }
            startActivity(intent)
        }

        movie.cast?.let { castList ->
            val actorAdapter = ActorAdapter(castList)
            binding.recyclerView.apply {
                setHasFixedSize(true)
                adapter = actorAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }

    }

    private fun ratingDone(movie: Movie) {
        binding.cvRate.setCardBackgroundColor(requireContext().getColor(R.color.main_color))
        binding.labelRate.text = "Rated"
        binding.ratingBar.rating = movie.user_rating
        binding.ivStar.setImageResource(R.drawable.ic_rated)
    }

    private fun isFavourite(movie: Movie) {
        if (movie.is_favourite) {
            binding.cvFavourite.setCardBackgroundColor(requireContext().getColor(R.color.main_color))
            binding.ivFavourite.imageTintList = requireContext().getColorStateList(R.color.white)
        } else {
            binding.cvFavourite.setCardBackgroundColor(requireContext().getColor(R.color.white))
            binding.ivFavourite.imageTintList = requireContext().getColorStateList(R.color.black)
        }
    }

}

