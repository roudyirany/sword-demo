package com.sword.demo.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sword.demo.R
import com.sword.demo.network.models.Breed

class BreedGridItemAdapter : RecyclerView.Adapter<BreedItemViewHolder>() {

    private val items = mutableListOf<Breed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedItemViewHolder {
        return BreedItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_breed_grid_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BreedItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun update(items: List<Breed>) {
        this.items.clear()
        add(items)
    }

    fun add(items: List<Breed>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}