package com.sword.demo.ui.home

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sword.demo.ui.home.adapter.BreedGridItemAdapter
import com.sword.demo.ui.home.adapter.BreedListItemAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
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

    @Provides
    @FragmentScoped
    fun provideLinearLayoutManager(
        @ActivityContext context: Context
    ) = LinearLayoutManager(context)

    @Provides
    @FragmentScoped
    fun provideStaggeredGridLayoutManager() =
        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
}