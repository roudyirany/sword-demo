package com.sword.demo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Singleton
    @Named("io_scheduler")
    @Provides
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Singleton
    @Named("main_scheduler")
    @Provides
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()
}