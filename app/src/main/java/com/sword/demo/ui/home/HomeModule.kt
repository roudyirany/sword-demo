package com.sword.demo.ui.home

import com.sword.demo.ui.home.adapter.BreedGridItemAdapter
import com.sword.demo.ui.home.adapter.BreedListItemAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class HomeModule {

    @Provides
    @FragmentScoped
    fun provideListItemAdapter() = BreedListItemAdapter()

    @Provides
    @FragmentScoped
    fun provideGridItemAdapter() = BreedGridItemAdapter()
}