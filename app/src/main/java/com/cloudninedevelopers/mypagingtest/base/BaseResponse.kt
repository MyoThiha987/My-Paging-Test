package com.cloudninedevelopers.mypagingtest.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseDataResponse<T>(
    @Json(name = "data")
    var data: T?,
    @Json(name = "message")
    var errorMessage: String
)

@JsonClass(generateAdapter = true)
data class BaseEmptyResponse(
    @Json(name = "message")
    var errorMessage: String
)

@JsonClass(generateAdapter = true)
data class BaseDataListResponse<T>(
    @Json(name = "results")
    var data: List<T>?

//    @Json(name = "message")
//    var errorMessage: String
)

data class BaseDataVO<T>(
    val list: List<T>
)
