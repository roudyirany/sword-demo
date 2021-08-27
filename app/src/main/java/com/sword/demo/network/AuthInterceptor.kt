package com.sword.demo.network

import com.sword.demo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        val newRequest: Request = request.newBuilder()
            .addHeader("x-api-key", BuildConfig.API_KEY)
            .build()
        return chain.proceed(newRequest)
    }
}