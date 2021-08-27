package com.sword.demo.base

import io.reactivex.rxjava3.disposables.CompositeDisposable

interface BaseDisposer {
    var disposeBag: CompositeDisposable
}