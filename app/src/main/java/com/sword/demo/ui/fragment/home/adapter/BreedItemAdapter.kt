package com.sword.demo.ui.fragment.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.sword.demo.network.models.Breed
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

abstract class BreedItemAdapter : RecyclerView.Adapter<BreedItemViewHolder>() {

    private val clicksSubject = PublishSubject.create<Breed>()

    abstract fun add(items: List<Breed>)

    protected fun onClick(item: Breed) {
        clicksSubject.onNext(item)
    }

    fun clicks(): Observable<Breed> {
        return clicksSubject.hide()
    }
}