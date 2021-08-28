package com.sword.demo.ui.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sword.demo.R
import com.sword.demo.network.models.Breed

class BreedSearchItemAdapter : RecyclerView.Adapter<BreedSearchItemViewHolder>() {

    private val items = mutableListOf<Breed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedSearchItemViewHolder {
        return BreedSearchItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_breed_search_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BreedSearchItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun add(items: List<Breed>) {
        this.items.addAll(items)
        notifyItemRangeInserted(this.items.size - items.size, items.size)
    }
}