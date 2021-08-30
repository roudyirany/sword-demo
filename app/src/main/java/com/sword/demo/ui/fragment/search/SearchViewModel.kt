package com.sword.demo.ui.fragment.search

import androidx.lifecycle.ViewModel
import com.sword.demo.network.ApiService
import com.sword.demo.network.models.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val apiService: ApiService,
    @Named("io_scheduler") private val ioScheduler: Scheduler,
    @Named("main_scheduler") private val mainScheduler: Scheduler
) : ViewModel() {

    fun searchBreed(name: CharSequence): Observable<List<Breed>> {
        return apiService.searchBreed(name.toString())
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
    }
}