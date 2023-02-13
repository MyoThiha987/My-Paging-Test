package com.cloudninedevelopers.mypagingtest.utils

import okio.IOException

data class NetworkException constructor(
    var errorBody : String ?= null,
    var errorCode : Int = 0
) : IOException()

class NoContentException : Exception() {}

