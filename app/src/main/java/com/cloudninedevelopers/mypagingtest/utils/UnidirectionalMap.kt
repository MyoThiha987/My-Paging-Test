package com.cloudninedevelopers.mypagingtest.utils

interface UnidirectionalMap<F, T> {
    fun map(item: F): T
}

interface UniMapper<I, O> {
    suspend fun map(data: I): O
}