package com.harshul.moviely.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harshul.moviely.data.models.Actor
import com.harshul.moviely.databinding.ItemActorBinding

class ActorAdapter(val list: List<Actor>) : RecyclerView.Adapter<ActorAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemActorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val actor = list[position]

        with(holder) {

            binding.textViewName.text = actor.name
            binding.tvDOB.text = actor.dob

            Glide.with(binding.imageViewMovie.context).load(actor.photo)
                .into(binding.imageViewMovie)
        }

    }
}