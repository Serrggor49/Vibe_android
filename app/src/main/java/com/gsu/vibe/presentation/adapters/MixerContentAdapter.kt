package com.gsu.vibe.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gsu.vibe.R
import com.gsu.vibe.data.models.MixerSoundModel

class MixerContentAdapter(private val mixerSoundModels: List<MixerSoundModel>, private val onItemClicked: (Pair<Int, MixerSoundModel>) -> Unit): RecyclerView.Adapter<MixerContentAdapter.MyViewHolder>() {

    private var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.mixer_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.isSelected = selectedItem == position // подсветка элемента
        holder.image?.setImageResource(mixerSoundModels[position].backimage)
        if(mixerSoundModels[position].isSelected){
            holder.shadow?.visibility = View.GONE
        }
        else{
            holder.shadow?.visibility = View.VISIBLE
        }

        holder.cardView?.setOnClickListener {
            onItemClicked(Pair(position, mixerSoundModels[position]))
        }

    }

    override fun getItemCount(): Int {
        return mixerSoundModels.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image: ImageView? = null
        var cardView: CardView? = null
        var shadow: View? = null
        init {
            image = itemView.findViewById(R.id.imageCard)
            cardView = itemView.findViewById(R.id.cardView)
            shadow = itemView.findViewById(R.id.shadowView)
        }
    }

}


