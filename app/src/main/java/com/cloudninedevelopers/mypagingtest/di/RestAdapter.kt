package com.cloudninedevelopers.mypagingtest.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.cloudninedevelopers.mypagingtest.utils.AuthTokenInterceptor
import com.cloudninedevelopers.mypagingtest.utils.NetworkExceptionInterceptor
import com.squareup.moshi.Moshi
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

fun createRetrofitClient(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
    val moshi = Moshi.Builder().build()
    return Retrofit.Builder().baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
}

fun createOkHttpClient(context: Context,chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
    builder.connectTimeout(60, TimeUnit.SECONDS)
    builder.writeTimeout(60, TimeUnit.SECONDS)
    builder.readTimeout(60, TimeUnit.SECONDS)
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    builder.networkInterceptors().add(httpLoggingInterceptor)
    builder.addInterceptor(AuthTokenInterceptor())
    builder.addInterceptor(chuckerInterceptor)
    //builder.addInterceptor(NetworkExceptionInterceptor())
    return builder.build()
}
