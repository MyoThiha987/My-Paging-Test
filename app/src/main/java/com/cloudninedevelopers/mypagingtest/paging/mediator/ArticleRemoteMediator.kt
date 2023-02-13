package com.cloudninedevelopers.mypagingtest.paging.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.cloudninedevelopers.mypagingtest.data.api.ArticleService
import com.cloudninedevelopers.mypagingtest.data.db.ArticleDatabase
import com.cloudninedevelopers.mypagingtest.data.mapper.ArticleResponseMapper
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleVO
import com.cloudninedevelopers.mypagingtest.data.vos.RemoteKeys
import com.cloudninedevelopers.mypagingtest.utils.getBody

private const val STARTING_PAGE_INDEX = 1

@ExperimentalPagingApi
class ArticleRemoteMediator(
    private val apiService: ArticleService,
    private val database: ArticleDatabase,
    private val mapper: ArticleResponseMapper
) : RemoteMediator<Int, ArticleVO>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleVO>
    ): MediatorResult {
        val page = when(loadType){
            LoadType.REFRESH ->{
                //get remotekey closet
                val remoteKey = getRemoteKeyClosetToCurrentPosition(state = state)
                remoteKey?.nextKey?.minus(1) ?: STARTING_PAGE_INDEX
            }
            LoadType.PREPEND ->{
                val remoteKeys = getRemoteKeyForFirstItem(state)
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                // We can return Success with `endOfPaginationReached = false` because Paging
                // will call this method again if RemoteKeys becomes non-null.
                // If remoteKeys is NOT NULL but its prevKey is null, that means we've reached
                // the end of pagination for prepend.
                val prevKey = remoteKeys?.prevKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevKey

            }
            LoadType.APPEND ->{
                val remoteKeys = getRemoteKeyForLastItem(state)
                // If remoteKeys is null, that means the refresh result is not in the database yet.
                // We can return Success with `endOfPaginationReached = false` because Paging
                // will call this method again if RemoteKeys becomes non-null.
                // If remoteKeys is NOT NULL but its nextKey is null, that means we've reached
                // the end of pagination for append.
                val nextKey = remoteKeys?.nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextKey
            }

        }
        return runCatching {
            val networkResponse = apiService.fetchArticleList(
                query = "cat",page= page, per_page = state.config.pageSize
            ).getBody()

            val endOfPaginationReached = networkResponse.data!!.isEmpty()
            database.withTransaction {
                //step 1 - clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().deleteAllRemoteKeys()
                    database.articleDao().deleteAllArticles()
                }
                val prevKey = if(page == STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if(endOfPaginationReached) null else page + 1
                val key = networkResponse.data?.map {articleResponse ->
                    RemoteKeys(id = articleResponse.id.orEmpty(), prevKey = prevKey,nextKey = nextKey)

                }
                if (key != null) {
                    database.remoteKeysDao().insertAllArticleKey(key)
                }
                database.articleDao().insertAllArticles(mapper.map(networkResponse).list)
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }

        }.getOrElse {
            return MediatorResult.Error(it)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ArticleVO>): RemoteKeys? {
        // Get the last page that was retrieved, that contained items.
        // From that last page, get the last item
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { articleVO ->
            articleVO.id.let { database.remoteKeysDao().fetchAllKeysArticleId(it) }
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ArticleVO>): RemoteKeys? {
        // Get the first page that was retrieved, that contained items.
        // From that first page, get the first item
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { articleVO ->
                articleVO.id.let { database.remoteKeysDao().fetchAllKeysArticleId(it) }
            }
    }

    private suspend fun getRemoteKeyClosetToCurrentPosition(state: PagingState<Int, ArticleVO>): RemoteKeys? {
        // The paging library is trying to load data after the anchor position
        // Get the item closest to the anchor position
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { articleId ->
                database.remoteKeysDao().fetchAllKeysArticleId(articleId)

            }
        }
    }
}