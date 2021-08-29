package com.sword.demo.ui.fragment.search

import com.sword.demo.ui.fragment.search.adapter.BreedSearchItemAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class SearchModule {

    @Provides
    @FragmentScoped
    fun provideBreedSearchItemAdapter() = BreedSearchItemAdapter()
}