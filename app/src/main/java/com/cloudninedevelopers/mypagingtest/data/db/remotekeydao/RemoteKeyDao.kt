package com.cloudninedevelopers.mypagingtest.data.db.remotekeydao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cloudninedevelopers.mypagingtest.data.vos.RemoteKeys
import retrofit2.http.DELETE


@Dao
interface RemoteKeyDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllArticleKey(remoteKey : List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE id = :id")
    fun fetchAllKeysArticleId(id : String) : RemoteKeys?

    @Query("DELETE FROM remote_keys")
    fun deleteAllRemoteKeys()
}