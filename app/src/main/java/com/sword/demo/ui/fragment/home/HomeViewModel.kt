package com.sword.demo.ui.fragment.home

import androidx.lifecycle.ViewModel
import com.sword.demo.base.BaseDisposer
import com.sword.demo.extensions.addSubscriptionTo
import com.sword.demo.network.ApiService
import com.sword.demo.network.models.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel(), BaseDisposer {

    companion object {
        const val DEFAULT_PAGE_LIMIT = 20
    }

    override var disposeBag = CompositeDisposable()

    private val breedsSubject = PublishSubject.create<Pair<List<Breed>, Boolean>>()

    private val breedsError = PublishSubject.create<Throwable>()

    private var lastIndex = 0

    var isLoading = false
        private set

    var isGridMode = false

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }

    fun getBreeds(
        page: Int = lastIndex,
        limit: Int = DEFAULT_PAGE_LIMIT
    ) {
        apiService.getBreeds(page, limit)
            .doOnSubscribe { isLoading = true }
            .doOnComplete { isLoading = false }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { items -> items.isNotEmpty() }
            .doOnNext { lastIndex = page + 1 }
            .subscribe({ items ->
                breedsSubject.onNext(items to (page > 0))
            }, { error ->
                breedsError.onNext(error)
            })
            .addSubscriptionTo(this)
    }

    fun breedsChanges(): Observable<Pair<List<Breed>, Boolean>> {
        getBreeds(0)
        return breedsSubject.hide()
    }

    fun errorOccurs(): Observable<Throwable> {
        return breedsError.hide()
    }
}