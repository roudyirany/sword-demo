package com.sword.demo.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseFragment : Fragment(), BaseDisposer {

    override lateinit var disposeBag: CompositeDisposable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposeBag = CompositeDisposable()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposeBag.clear()
    }
}