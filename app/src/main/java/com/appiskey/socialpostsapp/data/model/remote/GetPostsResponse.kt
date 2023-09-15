package com.appiskey.socialpostsapp.data.model.remote

import com.google.gson.annotations.SerializedName

data class GetPostsResponse(
    @SerializedName("hits")
    val posts: List<PostRemote>
)

data class PostRemote(
    val user: String,
    val tags: String,
    val likes: Int,
    val largeImageURL: String
)
