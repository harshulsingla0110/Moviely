package com.harshul.moviely.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harshul.moviely.databinding.ItemGenreBinding

class GenreAdapter(val list: List<String>, private val listener: OnGenreClickListener) :
    RecyclerView.Adapter<GenreAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val genre = list[position]

        with(holder) {
            binding.tvIcon.text = genre.split(" ")[0]
            binding.tvICategory.text = genre.split(" ")[1]

            binding.cvGenre.setOnClickListener { listener.onGenreClick(genre) }
        }

    }
}

interface OnGenreClickListener {
    fun onGenreClick(genre: String)
}