package com.sword.demo.ui.fragment.details

import com.sword.demo.constructors.ViewModelConstructor
import com.sword.demo.network.models.Breed
import com.sword.demo.stubs.stub
import junit.framework.Assert.assertEquals
import org.junit.Test

class DetailsViewModelTest {

    @Test
    fun whenDetailsViewModelIsInitialized_correctDataShouldBeStored() {
        val stubbedBreed = Breed.stub()

        val viewModel = ViewModelConstructor.detailsViewModelNewInstance(
            breed = stubbedBreed
        )

        assertEquals("name", viewModel.name)
        assertEquals("category", viewModel.category)
        assertEquals("origin", viewModel.origin)
        assertEquals("temperament", viewModel.temperament)
    }
}