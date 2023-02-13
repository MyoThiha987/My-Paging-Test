package com.cloudninedevelopers.mypagingtest.data.vos

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass

@Entity(tableName = "articles")
data class ArticleVO(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var description : String?,
    var urls : ArticlePhotoUrlsVO?,
    var user : ArticleUserVO?,
    var profile_image : ArticleUserProfilePhotoVO?

)

data class ArticlePhotoUrlsVO(
    var full : String?
)

data class ArticleUserVO(
    var name : String?
)

data class ArticleUserProfilePhotoVO(
    var large : String?
)
