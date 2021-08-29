package com.sword.demo.ui.fragment.home.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sword.demo.base.BaseViewHolder
import com.sword.demo.R
import com.sword.demo.network.models.Breed

class BreedItemViewHolder(itemView: View) : BaseViewHolder<Breed>,
    RecyclerView.ViewHolder(itemView) {

    private val breedImageView = itemView.findViewById<ImageView>(R.id.breed_image_view)
    private val breedTextView = itemView.findViewById<TextView>(R.id.breed_text_view)

    override fun bind(item: Breed) {
        Glide.with(itemView).load(item.image?.url).into(breedImageView)
        breedTextView.text = item.name
    }
}