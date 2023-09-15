package com.appiskey.socialpostsapp.data.api

import com.appiskey.socialpostsapp.data.model.remote.GetPostsResponse
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {

    @GET
    suspend fun getPosts(
        @Url url: String
    ): GetPostsResponse
}