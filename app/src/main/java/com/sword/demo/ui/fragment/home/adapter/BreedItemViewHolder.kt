package com.sword.demo.ui.fragment.home.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sword.demo.R
import com.sword.demo.base.BaseDisposer
import com.sword.demo.base.BaseViewHolder
import com.sword.demo.network.models.Breed
import io.reactivex.rxjava3.disposables.CompositeDisposable

class BreedItemViewHolder(itemView: View) : BaseViewHolder<Breed>,
    RecyclerView.ViewHolder(itemView), BaseDisposer {

    override lateinit var disposeBag: CompositeDisposable

    private val breedImageView = itemView.findViewById<ImageView>(R.id.breed_image_view)
    private val breedTextView = itemView.findViewById<TextView>(R.id.breed_text_view)

    override fun bind(item: Breed) {
        disposeBag = CompositeDisposable()
        Glide.with(itemView).load(item.image?.url).into(breedImageView)
        breedTextView.text = item.name
    }

    override fun recycle() {
        disposeBag.clear()
    }
}