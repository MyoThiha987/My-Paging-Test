package com.cloudninedevelopers.mypagingtest.data.db

import android.content.Context
import androidx.room.*
import com.cloudninedevelopers.mypagingtest.data.db.dao.ArticlesDao
import com.cloudninedevelopers.mypagingtest.data.db.remotekeydao.RemoteKeyDao
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleVO
import com.cloudninedevelopers.mypagingtest.data.vos.RemoteKeys


@Database(entities = [ArticleVO::class, RemoteKeys::class], version = 1, exportSchema = false)
@TypeConverters(ArticleListTypeConverter::class,ArticlePhotoUrlsTypeConverter::class,ArticleUserTypeConverter::class,ArticleUserProfileUrlTypeConverter::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticlesDao
    abstract fun remoteKeysDao(): RemoteKeyDao

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }


        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "Article.DB"
        ).allowMainThreadQueries().build()

    }


}