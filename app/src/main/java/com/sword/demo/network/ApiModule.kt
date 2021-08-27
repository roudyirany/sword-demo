package com.sword.demo.network

import com.sword.demo.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class ApiModule {

    @Singleton
    @Provides
    @Named("auth_interceptor")
    fun bindAuthInterceptor(): Interceptor {
        return AuthInterceptor()
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        @Named("auth_interceptor") authInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
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

    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}