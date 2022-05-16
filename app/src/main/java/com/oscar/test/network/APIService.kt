package com.oscar.test.network

import com.oscar.test.model.AccessToken
import com.oscar.test.model.Route
import retrofit2.http.*

interface APIService {

    @POST("login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): AccessToken

    @GET("route/{plate}")
    suspend fun getRoute(@Path("plate") plate: String): Route

}