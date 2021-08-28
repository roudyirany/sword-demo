package com.sword.demo.extensions

import com.sword.demo.base.BaseDisposer
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.addSubscriptionTo(disposer: BaseDisposer): Disposable {
    return this.apply { disposer.disposeBag.add(this) }
}