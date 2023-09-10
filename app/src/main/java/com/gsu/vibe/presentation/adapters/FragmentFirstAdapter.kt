package com.gsu.vibe.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.res.stringResource
import androidx.recyclerview.widget.RecyclerView
import com.gsu.vibe.R
import com.gsu.vibe.data.models.OnboardItemObject
import com.gsu.vibe.data.models.OnboardItemType
import kotlinx.coroutines.withContext

class FragmentFirstAdapter(val list: List<OnboardItemObject>) :
    RecyclerView.Adapter<FragmentFirstAdapter.MyViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when ((position.toDouble() / 4) % 1) {
            0.0 -> 0
            0.25 -> 1
            0.5 -> 2
            0.75 -> 3
            //1.0 -> 4
            else -> 3
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        Log.d("MyLogsTest", "viewType == ${viewType}")

        return when (viewType) {
            0 -> MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.onboard_first_type1, parent, false)
            )
            1 -> MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.onboard_first_type2, parent, false)
            )
            2 -> MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.onboard_first_type3, parent, false)
            )
            3 -> MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.onboard_first_type4, parent, false)
            )
            else -> MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.onboard_first_type1, parent, false)
            )
        }

    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

//        if((position.toDouble()/4)%1 == 0.0){
//            holder.image31?.setImageResource(list[position].items[0].first)
//            holder.image32?.setImageResource(list[position].items[1].first)
//        }
//
//        if((position.toDouble()/4)%1 == 0.25){
//            holder.image11?.setImageResource(list[position].items[0].first)
//        }
//
//        else if((position.toDouble()/4)%1== 0.5){
//            holder.image21?.setImageResource(list[position].items[0].first)
//            holder.image22?.setImageResource(list[position].items[1].first)
//            holder.image32?.setImageResource(list[position].items[2].first)
//
//        }
//        else if((position.toDouble()/4)%1 == 0.75){
//            holder.image31?.setImageResource(list[position].items[0].first)
//            holder.image32?.setImageResource(list[position].items[1].first)
//        }

//        Log.d("MyLogsTest", "position == ${position}")
//        Log.d("MyLogsTest", "onBindViewHolder position.toDouble())%4 == ${(position.toDouble() / 4) % 1}")



        if ((position.toDouble() / 4) % 1 == 0.0) {
            val lastDigit = position % 10
            if (lastDigit == 0 || lastDigit == 8 || lastDigit == 6) {
                holder.image11?.setImageResource(list[0].items[0].first)
                holder.tv11?.text = list[0].items[0].second
            } else {
                holder.image11?.setImageResource(list[4].items[0].first)
                holder.tv11?.text = list[4].items[0].second

            }

        } else if ((position.toDouble() / 4) % 1 == 0.25) {
            val lastDigit = position % 10
            if (lastDigit == 5 || lastDigit == 3 || lastDigit == 7) {
                holder.image21?.setImageResource(list[1].items[0].first)
                holder.tv21?.text = list[1].items[0].second
                holder.image22?.setImageResource(list[1].items[1].first)
                holder.tv22?.text = list[1].items[1
                ].second
                holder.image23?.setImageResource(list[1].items[2].first)
                holder.tv23?.text = list[1].items[2].second

            }
            else{
                holder.image21?.setImageResource(list[5].items[0].first)
                holder.tv21?.text = list[5].items[0].second
                holder.image22?.setImageResource(list[5].items[1].first)
                holder.tv22?.text = list[5].items[1].second
                holder.image23?.setImageResource(list[5].items[2].first)
                holder.tv23?.text = list[5].items[2].second
            }



        } else if ((position.toDouble() / 4) % 1 == 0.5) {

            val lastDigit = position % 10
            if (lastDigit == 6 || lastDigit == 4 || lastDigit == 2) {

                holder.image31?.setImageResource(list[2].items[0].first)
                holder.tv31?.text = list[2].items[0].second
                holder.image32?.setImageResource(list[2].items[1].first)
                holder.tv32?.text = list[2].items[1].second
            }
            else{
                holder.image31?.setImageResource(list[6].items[0].first)
                holder.tv31?.text = list[6].items[0].second
                holder.image32?.setImageResource(list[6].items[1].first)
                holder.tv32?.text = list[6].items[1].second
            }


        } else if ((position.toDouble() / 4) % 1 == 0.75) {

            Log.d("MyLogsTest", "position % 2 == ${position}")
            val lastDigit = position % 10

            if (lastDigit == 1 || lastDigit == 9 || lastDigit == 7) {
                Log.d("MyLogsTest", "lastDigit == ${lastDigit}")

                holder.image41?.setImageResource(list[3].items[0].first)
                holder.tv41?.text = list[3].items[0].second
                holder.image42?.setImageResource(list[3].items[1].first)
                holder.tv42?.text = list[3].items[1].second
                holder.image43?.setImageResource(list[3].items[2].first)
                holder.tv43?.text = list[3].items[2].second
            }
            else{
                Log.d("MyLogsTest", "lastDigit!!! == ${lastDigit}")

                holder.image41?.setImageResource(list[7].items[0].first)
                holder.tv41?.text = list[7].items[0].second
                holder.image42?.setImageResource(list[7].items[1].first)
                holder.tv42?.text = list[7].items[1].second
                holder.image43?.setImageResource(list[7].items[2].first)
                holder.tv43?.text = list[7].items[2].second

            }


        }

    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image11: ImageView? = itemView.findViewById(R.id.imageView11)
        val tv11: TextView? = itemView.findViewById(R.id.tv11)

        val image21: ImageView? = itemView.findViewById(R.id.imageView21)
        val tv21: TextView? = itemView.findViewById(R.id.tv21)
        val image22: ImageView? = itemView.findViewById(R.id.imageView22)
        val tv22: TextView? = itemView.findViewById(R.id.tv22)
        val image23: ImageView? = itemView.findViewById(R.id.imageView23)
        val tv23: TextView? = itemView.findViewById(R.id.tv23)

        val image31: ImageView? = itemView.findViewById(R.id.imageView31)
        val tv31: TextView? = itemView.findViewById(R.id.tv31)
        val image32: ImageView? = itemView.findViewById(R.id.imageView32)
        val tv32: TextView? = itemView.findViewById(R.id.tv32)

        val image41: ImageView? = itemView.findViewById(R.id.imageView41)
        val tv41: TextView? = itemView.findViewById(R.id.tv41)
        val image42: ImageView? = itemView.findViewById(R.id.imageView42)
        val tv42: TextView? = itemView.findViewById(R.id.tv42)
        val image43: ImageView? = itemView.findViewById(R.id.imageView43)
        val tv43: TextView? = itemView.findViewById(R.id.tv43)

        // val text: TextView = itemView.findViewById(R.id.nameCard)


    }


}

