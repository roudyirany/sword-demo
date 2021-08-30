package com.sword.demo.ui.fragment.home

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.sword.demo.base.BaseDisposer
import com.sword.demo.extensions.addSubscriptionTo
import com.sword.demo.network.ApiService
import com.sword.demo.network.models.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    @VisibleForTesting val apiService: ApiService,
    @Named("io_scheduler") private val ioScheduler: Scheduler,
    @Named("main_scheduler") private val mainScheduler: Scheduler
) : ViewModel(), BaseDisposer {

    companion object {
        const val DEFAULT_PAGE_LIMIT = 20
    }

    override var disposeBag = CompositeDisposable()

    private val breedsSubject = PublishSubject.create<Pair<List<Breed>, Boolean>>()

    private val breedsError = PublishSubject.create<Throwable>()

    @VisibleForTesting
    var lastIndex = 0

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
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
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
        return breedsSubject.hide()
    }

    fun errorOccurs(): Observable<Throwable> {
        return breedsError.hide()
    }
}