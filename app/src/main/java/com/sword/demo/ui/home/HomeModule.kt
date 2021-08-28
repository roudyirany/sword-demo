package com.sword.demo.ui.home

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sword.demo.ui.home.adapter.BreedGridItemAdapter
import com.sword.demo.ui.home.adapter.BreedItemAdapter
import com.sword.demo.ui.home.adapter.BreedListItemAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
class HomeModule {

    @Provides
    @FragmentScoped
    @Named("list_adapter")
    fun provideListItemAdapter(): BreedItemAdapter = BreedListItemAdapter()

    @Provides
    @FragmentScoped
    @Named("grid_adapter")
    fun provideGridItemAdapter(): BreedItemAdapter = BreedGridItemAdapter()

    @Provides
    @FragmentScoped
    @Named("linear_layout_manager")
    fun provideLinearLayoutManager(
        @ActivityContext context: Context
    ): RecyclerView.LayoutManager = LinearLayoutManager(context)

    @Provides
    @FragmentScoped
    @Named("grid_layout_manager")
    fun provideStaggeredGridLayoutManager(): RecyclerView.LayoutManager =
        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
}