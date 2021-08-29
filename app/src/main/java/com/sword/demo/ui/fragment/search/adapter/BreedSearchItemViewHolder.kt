package com.sword.demo.ui.fragment.search.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sword.demo.R
import com.sword.demo.base.BaseDisposer
import com.sword.demo.base.BaseViewHolder
import com.sword.demo.network.models.Breed
import io.reactivex.rxjava3.disposables.CompositeDisposable

class BreedSearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    BaseViewHolder<Breed>, BaseDisposer {

    override lateinit var disposeBag: CompositeDisposable

    private val breedNameTextView = itemView.findViewById<TextView>(R.id.breed_name_text_view)
    private val breedGroupValueTextView =
        itemView.findViewById<TextView>(R.id.breed_group_value_text_view)
    private val breedOriginValueTextView =
        itemView.findViewById<TextView>(R.id.breed_origin_value_text_view)

    override fun bind(item: Breed) {
        disposeBag = CompositeDisposable()
        breedNameTextView.text = item.name
        breedGroupValueTextView.text = item.group
        breedOriginValueTextView.text = item.origin
    }

    override fun recycle() {
        disposeBag.clear()
    }
}