package com.gabutproject.napapp.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabutproject.napapp.R
import com.gabutproject.napapp.convertDurationToFormatted
import com.gabutproject.napapp.convertNumericQualityToString
import com.gabutproject.napapp.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        private val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)
        private val qualityText: TextView = itemView.findViewById(R.id.quality_text)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)

                return ViewHolder(view)
            }
        }

        // this function used for set the item's data such as sleep quality, etc.
        fun bind(item: SleepNight) {
            val res = itemView.context.resources

            // sleep duration
            sleepLength.text =
                convertDurationToFormatted(item.startTimeMillis, item.endTimeMillis, res)
            // sleep quality
            qualityText.text = convertNumericQualityToString(item.sleepQuality, res)
            // sleep quality image
            qualityImage.setImageResource(
                when (item.sleepQuality) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_active
                }
            )
        }
    }

    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }


}