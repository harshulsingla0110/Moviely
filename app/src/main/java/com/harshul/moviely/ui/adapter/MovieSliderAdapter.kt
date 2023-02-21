package com.harshul.moviely.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harshul.moviely.data.models.Movie
import com.harshul.moviely.databinding.ItemMovieSliderBinding

class MovieSliderAdapter(
    var list: List<Movie>,
    val listener: OnMovieSliderClickListener
) :
    RecyclerView.Adapter<MovieSliderAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemMovieSliderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemMovieSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]

        with(holder) {

            binding.constraintLayoutMovie.setOnClickListener { listener.onClick(item) }

            Glide.with(binding.ivBanner.context).load(item.banner)
                .into(binding.ivBanner)
        }
    }
}

interface OnMovieSliderClickListener {
    fun onClick(movie: Movie)
}