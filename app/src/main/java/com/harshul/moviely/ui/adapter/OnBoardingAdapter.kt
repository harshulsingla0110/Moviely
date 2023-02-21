package com.harshul.moviely.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harshul.moviely.data.models.OnBoardingItemModel
import com.harshul.moviely.databinding.ItemIntroSliderBinding

class OnBoardingAdapter(var list: List<OnBoardingItemModel>) :
    RecyclerView.Adapter<OnBoardingAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemIntroSliderBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemIntroSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]

        with(holder) {
            binding.tvHead.text = item.title
            binding.tvSubHead.text = item.desc
            binding.imageView.setImageResource(item.image)
        }
    }
}