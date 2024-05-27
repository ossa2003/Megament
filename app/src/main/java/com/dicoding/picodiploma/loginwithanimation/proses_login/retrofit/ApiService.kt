package com.dicoding.picodiploma.loginwithanimation.proses_login.retrofit

import com.dicoding.picodiploma.loginwithanimation.proses_login.response.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}
