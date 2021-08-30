package com.sword.demo.ui.fragment.home

import com.sword.demo.constructors.ViewModelConstructor
import com.sword.demo.network.models.Breed
import com.sword.demo.stubs.stub
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import java.io.IOException
import java.util.concurrent.TimeUnit

class HomeViewModelTest {

    @Test
    fun whenGetBreedsIsCalledWithPage0_breedListShouldBeReceived_andNextPageShouldBeUpdated() {
        val scheduler = TestScheduler()

        val viewModel = ViewModelConstructor.homeViewModelNewInstance(
            ioScheduler = scheduler,
            mainScheduler = scheduler
        )

        val stubbedBreed = Breed.stub()

        `when`(viewModel.apiService.getBreeds(any(), any())).thenReturn(
            Observable.just(
                listOf(stubbedBreed)
            )
        )

        val observable = viewModel.breedsChanges().test()

        viewModel.getBreeds(0)

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        observable.assertValue { (items, shouldScroll) ->
            items == listOf(stubbedBreed) && !shouldScroll
                    && !viewModel.isLoading
                    && viewModel.lastIndex == 1
        }
    }

    @Test
    fun whenGetBreedsIsCalledWithPageBiggerThan0_breedListShouldBeReceived_shouldScroll_andNextPageShouldBeUpdated() {
        val scheduler = TestScheduler()

        val viewModel = ViewModelConstructor.homeViewModelNewInstance(
            ioScheduler = scheduler,
            mainScheduler = scheduler
        )

        val stubbedBreed = Breed.stub()

        `when`(viewModel.apiService.getBreeds(any(), any())).thenReturn(
            Observable.just(
                listOf(stubbedBreed)
            )
        )

        val observable = viewModel.breedsChanges().test()

        viewModel.getBreeds(1)

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        observable.assertValue { (items, shouldScroll) ->
            items == listOf(stubbedBreed) && shouldScroll
                    && !viewModel.isLoading
                    && viewModel.lastIndex == 2
        }
    }

    @Test
    fun whenGetBreedsIsCalled_andErrorOccurs_errorShouldBeReturned() {
        val scheduler = TestScheduler()

        val viewModel = ViewModelConstructor.homeViewModelNewInstance(
            ioScheduler = scheduler,
            mainScheduler = scheduler
        )

        `when`(viewModel.apiService.getBreeds(any(), any())).thenReturn(
            Observable.error(IOException())
        )

        val observable = viewModel.errorOccurs().test()

        viewModel.getBreeds(0)

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        observable.assertValue { throwable ->
            throwable is IOException
        }
    }
}