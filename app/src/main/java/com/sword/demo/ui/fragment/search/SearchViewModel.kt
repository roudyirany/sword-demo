package com.sword.demo.ui.fragment.search

import androidx.lifecycle.ViewModel
import com.sword.demo.network.ApiService
import com.sword.demo.network.models.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    fun searchBreed(name: CharSequence): Observable<List<Breed>> {
        return apiService.searchBreed(name.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}