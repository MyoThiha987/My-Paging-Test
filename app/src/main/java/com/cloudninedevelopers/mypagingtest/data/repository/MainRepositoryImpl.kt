package com.cloudninedevelopers.mypagingtest.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cloudninedevelopers.mypagingtest.data.api.ArticleService
import com.cloudninedevelopers.mypagingtest.data.db.ArticleDatabase
import com.cloudninedevelopers.mypagingtest.data.mapper.ArticleResponseMapper
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleVO
import com.cloudninedevelopers.mypagingtest.paging.mediator.ArticleRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 10
private const val NETWORK_PAGE_SIZE = 10000

class MainRepositoryImpl @Inject constructor(
    private val service: ArticleService,
    private val database: ArticleDatabase,
    private val mapper: ArticleResponseMapper
) : MainRepository {
    override fun fetchArticleList(): Flow<PagingData<ArticleVO>> {

        //Network Only
        /*return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
                initialLoadSize = ITEMS_PER_PAGE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ArticlePagingSource(service,mapper) }
        ).flow*/

        //Network into Database
        val pagingSourceFactory = { database.articleDao().fetchArticles() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                initialLoadSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = ArticleRemoteMediator(service, database, mapper),
            pagingSourceFactory = pagingSourceFactory
        ).flow

    }
}
