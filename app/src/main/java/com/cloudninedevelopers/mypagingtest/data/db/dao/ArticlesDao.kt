package com.cloudninedevelopers.mypagingtest.data.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleVO

@Dao
interface ArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllArticles(data : List<ArticleVO>)

    @Query("SELECT * FROM articles")
    fun fetchArticles() : PagingSource<Int,ArticleVO>

    @Query("DELETE FROM articles")
    fun deleteAllArticles()
}