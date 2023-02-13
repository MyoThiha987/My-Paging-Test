package com.cloudninedevelopers.mypagingtest.utils

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = CLIENT_ID
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Authorization", "Client-ID ${token}")
        val newRequest = requestBuilder.build()
        return chain.proceed(newRequest)
    }
}