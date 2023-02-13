package com.cloudninedevelopers.mypagingtest.data.repository

import androidx.paging.PagingData
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleVO
import kotlinx.coroutines.flow.Flow

interface MainRepository {
     fun fetchArticleList() : Flow<PagingData<ArticleVO>>
}