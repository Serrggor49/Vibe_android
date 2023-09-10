package com.gsu.vibe.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.gsu.vibe.R
import com.gsu.vibe.data.models.SoundModel

class MixerContentAdapter(private val mixerSoundModels: List<SoundModel>, private val onItemClicked: (Pair<Int, SoundModel>) -> Unit): RecyclerView.Adapter<MixerContentAdapter.MyViewHolder>() {

    private var selectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.mixer_item, parent, false))
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.isSelected = selectedItem == position // подсветка элемента
        holder.image?.setImageResource(mixerSoundModels[position].mixerIcon)
        if(mixerSoundModels[position].isSelected){
            holder.shadow?.visibility = View.GONE
            holder.cardView?.strokeWidth = 3
        }
        else{
            holder.shadow?.visibility = View.GONE
            holder.cardView?.strokeWidth = 0

        }

        holder.cardView?.setOnClickListener {
            onItemClicked(Pair(position, mixerSoundModels[position]))
        }

        holder.cardView!!.setOnTouchListener { v, event -> animPress(v, event, (Pair(position, mixerSoundModels[position]))) }

    }

    override fun getItemCount(): Int {
        return mixerSoundModels.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image: ImageView? = null
        var cardView: MaterialCardView? = null
        var shadow: View? = null
        init {
            image = itemView.findViewById(R.id.imageCard)
            cardView = itemView.findViewById(R.id.cardView)
            shadow = itemView.findViewById(R.id.shadowView)
        }
    }


    fun animPress(v: View, event: MotionEvent, pair: Pair<Int, SoundModel>): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                v.animate().scaleX(0.96f).scaleY(0.96f).setDuration(200).start()
            }
            MotionEvent.ACTION_UP -> {
                // Пользователь отпустил кнопку
                v.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(100)
                    .withEndAction {
                        onItemClicked(pair)
//                        mainViewmodel.setCurrentSound(currentSound)
//                        val action = MeditationFragmentDirections.actionMeditationFragmentToPlayerFragment()
//                        view?.findNavController()?.navigate(action)
                    }
                    .start()
                //Log.d("MY_l124", "ACTION_UP")
            }
            MotionEvent.ACTION_CANCEL -> {
                v.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
            }
            MotionEvent.ACTION_MOVE -> {
            }

            MotionEvent.ACTION_BUTTON_RELEASE -> {
            }
        }
        return true
    }



}


