package com.mdlicht.zb.simpleurlshortener.network

import com.mdlicht.zb.simpleurlshortener.model.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ShortenService {
    @GET("shorturl")
    fun shortenUrl(@Query("url") url: String): Single<Response>
}