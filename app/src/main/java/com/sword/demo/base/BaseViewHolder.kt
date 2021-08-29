package com.sword.demo.base

interface BaseViewHolder<T> {
    fun bind(item: T)
    fun recycle()
}