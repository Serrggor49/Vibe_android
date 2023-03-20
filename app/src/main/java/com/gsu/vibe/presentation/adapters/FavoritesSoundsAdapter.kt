package com.gsu.vibe.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.gsu.vibe.R
import com.gsu.vibe.data.models.SoundModel

class FavoritesSoundsAdapter(private val sounds: List<SoundModel>,
                             private val context: Context,
                             private val onItemClicked: (name: String) -> Unit): RecyclerView.Adapter<FavoritesSoundsAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorites_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.soundName.text = context.getString(sounds[position].title)
        holder.typeSoundTextView.text = context.getString(sounds[position].subtitle)
        holder.previewImageF.setImageResource(sounds[position].previewF)
        holder.previewImageB.setImageResource(sounds[position].previewB)
        holder.constraintItemView.setOnClickListener {
            onItemClicked(sounds[position].name)
        }

    }

    override fun getItemCount(): Int {
        return  sounds.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val constraintItemView: ConstraintLayout
        val soundName: TextView
        val typeSoundTextView: TextView
        val previewImageF: ImageView
        val previewImageB: ImageView

        init {
            constraintItemView = itemView.findViewById(R.id.constraintItemView)
            soundName = itemView.findViewById(R.id.soundName)
            typeSoundTextView = itemView.findViewById(R.id.typeSoundTextView)
            previewImageF = itemView.findViewById(R.id.previewImageF)
            previewImageB = itemView.findViewById(R.id.previewImageB)
        }

    }

}


