package com.cloudninedevelopers.mypagingtest.data.api

import com.cloudninedevelopers.mypagingtest.data.response.ArticleResponse
import com.cloudninedevelopers.mypagingtest.base.BaseDataListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("search/photos")
    suspend fun fetchArticleList(
        @Query("query") query : String,
        @Query("page") page : Int,
        @Query("per_page") per_page : Int,
    ) : Response<BaseDataListResponse<ArticleResponse>>
}