package com.cloudninedevelopers.mypagingtest.di

import com.cloudninedevelopers.mypagingtest.data.api.ArticleService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import javax.inject.Named


@Module
@InstallIn(ViewModelComponent::class)
object ServiceModule {
    @Provides
    fun provideArticleService(@Named("MVVMClient") retrofit: Retrofit): ArticleService {
        return retrofit.create(ArticleService::class.java)
    }
}