package com.harshul.moviely.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harshul.moviely.data.models.ItemGrid
import com.harshul.moviely.databinding.HomeGridItemBinding

class HomeGridAdapter(
    private val list: List<ItemGrid>,
    val listener: OnHomeItemClick
) :
    RecyclerView.Adapter<HomeGridAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: HomeGridItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            HomeGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        with(holder) {
            binding.textView.text = item.title
            binding.ivIcon.setImageResource(item.image)

            if (position > 0) binding.ivSoon.visibility = View.VISIBLE

             binding.clHomeCard.setOnClickListener { listener.onHomeItemClick(position) }

        }
    }
}

interface OnHomeItemClick {
    fun onHomeItemClick(position: Int)
}