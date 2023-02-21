package com.harshul.moviely.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harshul.moviely.data.models.Movie
import com.harshul.moviely.databinding.ItemFavouriteBinding

class FavouriteAdapter(
    val list: List<Movie>,
    val listener: OnFavClickListener
) :
    RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemFavouriteBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemFavouriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = list[position]

        with(holder) {
            binding.textViewName.text = movie.title
            binding.textViewRating.text = "⭐ ${movie.rating}"

            Glide.with(binding.imageViewMovie.context).load(movie.thumbnail)
                .into(binding.imageViewMovie)

            binding.layoutFavMovie.setOnClickListener { listener.onClick(movie) }
        }
    }
}

interface OnFavClickListener {
    fun onClick(movie: Movie)
}