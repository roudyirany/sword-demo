package com.sword.demo.ui.fragment.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding4.view.clicks
import com.sword.demo.R
import com.sword.demo.extensions.addSubscriptionTo
import com.sword.demo.network.models.Breed
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

class BreedSearchItemAdapter : RecyclerView.Adapter<BreedSearchItemViewHolder>() {

    private val items = mutableListOf<Breed>()

    private val clicksSubject = PublishSubject.create<Breed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedSearchItemViewHolder {
        return BreedSearchItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_breed_search_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BreedSearchItemViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView
            .clicks()
            .subscribe { clicksSubject.onNext(items[holder.adapterPosition]) }
            .addSubscriptionTo(holder)
    }

    override fun onViewRecycled(holder: BreedSearchItemViewHolder) {
        super.onViewRecycled(holder)
        holder.recycle()
    }

    override fun getItemCount() = items.size

    private fun onClick(item: Breed) {
        clicksSubject.onNext(item)
    }

    fun update(items: List<Breed>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clicks(): Observable<Breed> {
        return clicksSubject.hide()
    }
}