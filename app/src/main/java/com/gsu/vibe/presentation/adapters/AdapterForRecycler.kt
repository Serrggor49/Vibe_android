package com.gsu.vibe.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gsu.vibe.R
import com.gsu.vibe.databinding.Item2Binding

class AdapterForRecycler(private val list: List<Pair<Int, Int>>) : RecyclerView.Adapter<AdapterForRecycler.MyViewHolder>() {

    lateinit var binding: Item2Binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = Item2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.image.setImageResource(list[position % list.size].first)
        holder.text.text = holder.text.context.getString((list[position % list.size].second))
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    class MyViewHolder(binding: Item2Binding) : RecyclerView.ViewHolder(binding.root){
        val image: ImageView = binding.imageView36
        val text: TextView = binding.nameCard
    }

}


