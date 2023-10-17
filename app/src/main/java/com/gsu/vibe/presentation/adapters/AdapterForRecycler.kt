package com.gsu.vibe.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gsu.vibe.R

class AdapterForRecycler(private val list: List<Pair<Int, Int>>) : RecyclerView.Adapter<AdapterForRecycler.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item2, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.image.setImageResource(list[position % list.size].first)
        holder.text.text = holder.text.context.getString((list[position % list.size].second))
    }

    override fun getItemCount(): Int {

        return Int.MAX_VALUE
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.imageView36)
        val text: TextView = itemView.findViewById(R.id.nameCard)
    }



}


