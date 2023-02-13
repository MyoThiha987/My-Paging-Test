package com.cloudninedevelopers.mypagingtest.di


import com.cloudninedevelopers.mypagingtest.data.repository.MainRepository
import com.cloudninedevelopers.mypagingtest.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindArticleRepository(repo: MainRepositoryImpl): MainRepository

}