package com.example.retrofit.api

import com.example.retrofit.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface simpleApi {

    @GET("posts/1")
    suspend fun getPost() : Response<Post>
}