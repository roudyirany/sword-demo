package com.sword.demo.base

import androidx.recyclerview.widget.RecyclerView


abstract class BasePaginationScrollListener() :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (!recyclerView.canScrollVertically(1)) {
            loadMoreItems()
        }
    }

    protected abstract fun loadMoreItems()
    abstract val isLoading: Boolean
}