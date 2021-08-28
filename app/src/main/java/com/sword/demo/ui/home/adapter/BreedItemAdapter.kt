package com.sword.demo.ui.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sword.demo.network.models.Breed

abstract class BreedItemAdapter : RecyclerView.Adapter<BreedItemViewHolder>() {

    abstract fun add(items: List<Breed>)
}