package com.harshul.moviely.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harshul.moviely.data.models.Movie
import com.harshul.moviely.databinding.ItemMovieBoxBinding

class MovieHorizontalAdapter(
    val list: List<Movie>,
    private val listener: OnMovieHorizontalListener
) : RecyclerView.Adapter<MovieHorizontalAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemMovieBoxBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemMovieBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = list[position]

        with(holder) {
            binding.textViewName.text = movie.title
            binding.textViewYear.text = movie.release_date.split(" ")[2]
            binding.textViewRating.text = movie.rating.toString()

            Glide.with(binding.imageViewMovie.context).load(movie.thumbnail)
                .into(binding.imageViewMovie)

            binding.cvMovie.setOnClickListener { listener.onClick(movie) }
        }
    }
}

interface OnMovieHorizontalListener {
    fun onClick(movie: Movie)
}