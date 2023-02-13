package com.cloudninedevelopers.mypagingtest.paging.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cloudninedevelopers.mypagingtest.data.api.ArticleService
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleVO
import com.cloudninedevelopers.mypagingtest.data.mapper.ArticleResponseMapper
import com.cloudninedevelopers.mypagingtest.data.response.ArticleResponse
import com.cloudninedevelopers.mypagingtest.utils.getBody
import kotlinx.coroutines.delay

private const val STARTING_PAGE_INDEX = 1
private const val LOAD_DELAY_MILLIS = 6_000L

class ArticlePagingSource (
    private val apiService: ArticleService,
    private val articleMapper: ArticleResponseMapper
) : PagingSource<Int, ArticleVO>() {

    override fun getRefreshKey(state: PagingState<Int, ArticleVO>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleVO> {
        val startKey = params.key ?: STARTING_PAGE_INDEX

        return runCatching {
            val networkResponse = apiService.fetchArticleList(
                "cat",
                startKey,
                params.loadSize
            ).getBody()

            val result = articleMapper.map(networkResponse)
            //Log.i("DATA","$result")

            return LoadResult.Page(
                data = result.list,
                prevKey = if (startKey == STARTING_PAGE_INDEX) null else startKey - 1,
                nextKey = if (result.list.isEmpty()) null else startKey + 1
            )

        }.getOrElse {
            Log.i("T", it.toString())
            LoadResult.Error(it)
        }
    }
}