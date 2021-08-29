package com.sword.demo.ui.fragment.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sword.demo.network.models.Breed
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val breed: Breed? = savedStateHandle["breed"]

    val name = breed?.name

    val category = breed?.category

    val origin = breed?.origin

    val temperament = breed?.temperament
}