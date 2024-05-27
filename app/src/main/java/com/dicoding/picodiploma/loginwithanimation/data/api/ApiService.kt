package com.dicoding.picodiploma.loginwithanimation.data.api

import com.dicoding.picodiploma.loginwithanimation.data.response.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import com.dicoding.picodiploma.loginwithanimation.data.response.GetAssetsResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("assets")
    suspend fun getAssets(
        @Header("Authorization") token: String,
    ) : GetAssetsResponse

}
