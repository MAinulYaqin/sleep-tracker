package com.gabutproject.napapp.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gabutproject.napapp.database.SleepNight
import com.gabutproject.napapp.databinding.ListItemSleepNightBinding

class SleepNightAdapter :
    ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {

    // this method tell the recyclerView of each item's position,
    // you can make 'unique' item on your own here
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    // this method tell the recyclerView which layout it should be shown,
    // ViewHolder.from handle the configuration.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    // this subClass holds item's resources such as the Text or imageView.
    // simply calling, a template of each item
    class ViewHolder private constructor(private val binding: ListItemSleepNightBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ListItemSleepNightBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

        // this function used for set the item's data such as sleep quality, etc.
        fun bind(item: SleepNight) {
            binding.sleep = item
            binding.executePendingBindings()
        }
    }

    // this class used for telling the recyclerView if there's any item has changed
    class SleepNightDiffCallback : DiffUtil.ItemCallback<SleepNight>() {
        override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            // check if the newItem's id and the oldItem's id are the same or not
            // this checking item, so we need to specify the id to tell the differences
            return oldItem.nightId == newItem.nightId
        }

        override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            // check if the newContent and the oldContent are the same or not
            return oldItem == newItem
        }
    }
}