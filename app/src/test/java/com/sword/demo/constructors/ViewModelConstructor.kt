package com.sword.demo.constructors

import com.sword.demo.network.ApiService
import com.sword.demo.ui.fragment.home.HomeViewModel
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
    }
}