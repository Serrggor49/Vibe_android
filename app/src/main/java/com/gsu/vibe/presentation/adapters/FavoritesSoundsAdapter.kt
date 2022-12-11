package com.gsu.vibe.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.gsu.vibe.R
import com.gsu.vibe.data.models.SoundModel

class FavoritesSoundsAdapter(private val sounds: List<SoundModel>): RecyclerView.Adapter<FavoritesSoundsAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorites_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.soundName.text = sounds[position].title
        holder.typeSoundTextView.text = sounds[position].subtitle
    }

    override fun getItemCount(): Int {
        return  sounds.size
    }



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

       // val constraintItemView: ConstraintLayout? = null
        val soundName: TextView
        val typeSoundTextView: TextView

        init {
         //   constraintItemView = itemView.findViewById(R.id.constraintItemView)
            soundName = itemView.findViewById(R.id.soundName)
            typeSoundTextView = itemView.findViewById(R.id.typeSoundTextView)
        }

    }

}


