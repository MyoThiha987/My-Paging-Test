package com.cloudninedevelopers.mypagingtest.utils

import com.cloudninedevelopers.mypagingtest.base.BaseDataVO

fun <T> List<T>.toDataVO(): BaseDataVO<T> {
    return BaseDataVO(
        this
    )
}