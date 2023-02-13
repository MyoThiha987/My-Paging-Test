package com.cloudninedevelopers.mypagingtest.data.mapper

import com.cloudninedevelopers.mypagingtest.data.response.ArticleResponse
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleVO
import com.cloudninedevelopers.mypagingtest.base.BaseDataListResponse
import com.cloudninedevelopers.mypagingtest.base.BaseDataVO
import com.cloudninedevelopers.mypagingtest.data.vos.ArticlePhotoUrlsVO
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleUserProfilePhotoVO
import com.cloudninedevelopers.mypagingtest.data.vos.ArticleUserVO
import com.cloudninedevelopers.mypagingtest.utils.UniMapper
import com.cloudninedevelopers.mypagingtest.utils.UnidirectionalMap
import com.cloudninedevelopers.mypagingtest.utils.toDataVO
import javax.inject.Inject


class ArticleResponseMapper @Inject constructor() :
    UnidirectionalMap<BaseDataListResponse<ArticleResponse>?, BaseDataVO<ArticleVO>> {
    override fun map(data: BaseDataListResponse<ArticleResponse>?): BaseDataVO<ArticleVO> {
        return data?.data?.map {
            ArticleVO(
                id = it.id.orEmpty(),
                description = it.description,
                urls = ArticlePhotoUrlsVO(it.urls?.full),
                user = ArticleUserVO(it.user?.name),
                profile_image = ArticleUserProfilePhotoVO(it.profile_image?.large)

            )
        }.orEmpty().toDataVO()


    }

}

/*
class ArticleResponseMapper @Inject constructor() :
    UnidirectionalMap<ArticleResponse, ArticleVO> {
    override fun map(data: ArticleResponse): ArticleVO {
       return ArticleVO(
           id = data.id,
           description = data.description,
           urls = ArticlePhotoUrlsVO(data.urls?.full),
           user = ArticleUserVO(data.user?.name),
           profile_image = ArticleUserProfilePhotoVO(data.profile_image?.large)

       )


    }

}
*/
