package com.example.myweather2.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myweather2.R

class HomeAdapter(
        private val onClick: (String) -> Unit,
        private val onDelete: (String) -> Unit
) : ListAdapter<String, HomeAdapter.ViewHolder>(
        CourseDiffCallback()
) {

    class ViewHolder(
            view: View,
            private val onClick: (Int) -> Unit,
            private val onDelete: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textViewCityName)

        init {
            view.setOnClickListener { onClick(adapterPosition) }
            view.findViewById<ImageButton>(R.id.buttonDeleteCity).setOnClickListener {
                onDelete(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.city_item, parent, false)

        return ViewHolder(
                view,
                { position -> onClick(getItem(position)) },
                { position -> onDelete(getItem(position)) })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = getItem(position)
    }
}

class CourseDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}