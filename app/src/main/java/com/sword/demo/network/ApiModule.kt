package com.sword.demo.network

import android.content.Context
import com.sword.demo.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class ApiModule {

    @ViewModelScoped
    @Provides
    @Named("auth_interceptor")
    fun provideAuthInterceptor(): Interceptor {
        return AuthInterceptor()
    }

    @ViewModelScoped
    @Provides
    @Named("cache_interceptor")
    fun provideCacheInterceptor(): Interceptor {
        return CacheInterceptor()
    }

    @ViewModelScoped
    @Provides
    fun provideCache(
        @ApplicationContext context: Context
    ) = Cache(context.cacheDir, (5 * 1024 * 1024).toLong())

    @ViewModelScoped
    @Provides
    fun provideHttpClient(
        @Named("auth_interceptor") authInterceptor: Interceptor,
        @Named("cache_interceptor") cacheInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(cacheInterceptor)
            .cache(cache)
            .build()
    }

    @ViewModelScoped
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @ViewModelScoped
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}