package com.sword.demo.ui.home

import androidx.lifecycle.ViewModel
import com.sword.demo.network.ApiService
import com.sword.demo.network.models.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    fun getBreeds(): Observable<List<Breed>> {
        return apiService.getBreeds(0, 20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}