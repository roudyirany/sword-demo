package com.sword.demo.constructors

import androidx.lifecycle.SavedStateHandle
import com.sword.demo.network.ApiService
import com.sword.demo.network.models.Breed
import com.sword.demo.stubs.stub
import com.sword.demo.ui.fragment.details.DetailsViewModel
import com.sword.demo.ui.fragment.home.HomeViewModel
import com.sword.demo.ui.fragment.search.SearchViewModel
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.mockito.Mockito.mock

class ViewModelConstructor {

    companion object {
        fun homeViewModelNewInstance(
            apiService: ApiService = mock(ApiService::class.java),
            ioScheduler: Scheduler = TestScheduler(),
            mainScheduler: Scheduler = TestScheduler()
        ) = HomeViewModel(
            apiService = apiService,
            ioScheduler = ioScheduler,
            mainScheduler = mainScheduler
        )

        fun searchViewModelNewInstance(
            apiService: ApiService = mock(ApiService::class.java),
            ioScheduler: Scheduler = TestScheduler(),
            mainScheduler: Scheduler = TestScheduler()
        ) = SearchViewModel(
            apiService = apiService,
            ioScheduler = ioScheduler,
            mainScheduler = mainScheduler
        )

        fun detailsViewModelNewInstance(
            breed: Breed = Breed.stub()
        ) = DetailsViewModel(
            savedStateHandle = SavedStateHandle().apply { set("breed", breed) }
        )
    }
}