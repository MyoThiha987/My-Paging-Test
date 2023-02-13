package com.cloudninedevelopers.mypagingtest.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.cloudninedevelopers.mypagingtest.data.vos.ArticlePhotoUrlsVO
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleUserProfilePhotoVO
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleUserVO
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class ArticleListTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToObj(data: String): List<ArticleVO> {
        val objType = object : TypeToken<List<ArticleVO>>() {}.type
        return gson.fromJson(data, objType)
    }

    @TypeConverter
    fun objToString(obj: List<ArticleVO>): String {
        return gson.toJson(obj)
    }
}

class ArticlePhotoUrlsTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToObj(data : String) : ArticlePhotoUrlsVO {
        val objType = object : TypeToken<ArticlePhotoUrlsVO>() {}.type
        return gson.fromJson(data,objType)
    }

    @TypeConverter
    fun objToString(obj : ArticlePhotoUrlsVO) : String{
        return gson.toJson(obj)
    }


}

class ArticleUserTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToObj(data : String) : ArticleUserVO {
        val objType = object : TypeToken<ArticleUserVO>() {}.type
        return gson.fromJson(data,objType)
    }

    @TypeConverter
    fun objToString(obj : ArticleUserVO) : String{
        return gson.toJson(obj)
    }
}

class ArticleUserProfileUrlTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToObj(data : String) : ArticleUserProfilePhotoVO {
        val objType = object : TypeToken<ArticleUserProfilePhotoVO>() {}.type
        return gson.fromJson(data,objType)
    }

    @TypeConverter
    fun objToString(obj : ArticleUserProfilePhotoVO) : String{
        return gson.toJson(obj)
    }
}


