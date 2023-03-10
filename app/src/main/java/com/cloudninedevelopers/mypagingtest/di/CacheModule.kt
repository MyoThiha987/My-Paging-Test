package com.cloudninedevelopers.mypagingtest.di

import android.content.Context
import com.cloudninedevelopers.mypagingtest.data.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ArticleDatabase {
        return ArticleDatabase.getInstance(context)
    }
}

