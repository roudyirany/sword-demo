package com.sword.demo.ui.fragment.search

import com.sword.demo.constructors.ViewModelConstructor
import com.sword.demo.network.models.Breed
import com.sword.demo.stubs.stub
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import java.io.IOException
import java.util.concurrent.TimeUnit

class SearchViewModelTest {

    @Test
    fun whenGetQueryIsCalled_resultsShouldBeReturned() {
        val scheduler = TestScheduler()

        val viewModel = ViewModelConstructor.searchViewModelNewInstance(
            ioScheduler = scheduler,
            mainScheduler = scheduler
        )

        val stubbedBreed = Breed.stub()

        `when`(viewModel.apiService.searchBreed(anyString())).thenReturn(
            Observable.just(
                listOf(stubbedBreed)
            )
        )

        val observable = viewModel.searchBreed("query").test()

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        observable.assertValue { items ->
            items == listOf(stubbedBreed)
        }
    }

    @Test
    fun whenGetQueryIsCalled_andErrorOccurs_errorShouldBeReturned() {
        val scheduler = TestScheduler()

        val viewModel = ViewModelConstructor.searchViewModelNewInstance(
            ioScheduler = scheduler,
            mainScheduler = scheduler
        )

        `when`(viewModel.apiService.searchBreed(anyString())).thenReturn(
            Observable.error(
                IOException()
            )
        )

        val observable = viewModel.searchBreed("query").test()

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        observable.assertError { throwable ->
            throwable is IOException
        }
    }
}