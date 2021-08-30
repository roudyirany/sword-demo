package com.sword.demo.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CacheInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        val newRequest: Request = request.newBuilder()
            .addHeader("Cache-Control", "public, max-age=5")
            .build()
        return chain.proceed(newRequest)
    }
}