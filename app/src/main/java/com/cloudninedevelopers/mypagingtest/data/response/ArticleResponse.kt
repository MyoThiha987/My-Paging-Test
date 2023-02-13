package com.cloudninedevelopers.mypagingtest.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    @Json(name = "id")
    var id: String?,
    @Json(name = "description")
    var description : String?,
    @Json(name = "urls")
    var urls : ArticlePhotoUrlsResponse?,
    @Json(name = "user")
    var user : ArticleUserResponse?,
    @Json(name = "profile_image")
    var profile_image : ArticleUserProfilePhotoResponse?
    )

@JsonClass(generateAdapter = true)
data class ArticlePhotoUrlsResponse(
    @Json(name = "full")
    var full : String?
)

@JsonClass(generateAdapter = true)
data class ArticleUserResponse(
    @Json(name = "name")
    var name : String?
)

@JsonClass(generateAdapter = true)
data class ArticleUserProfilePhotoResponse(
    @Json(name = "large")
    var large : String?
)