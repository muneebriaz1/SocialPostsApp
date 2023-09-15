package com.appiskey.socialpostsapp.data.api

import com.appiskey.socialpostsapp.utils.Constants


class ApiHelper(private val apiService: ApiService) {

    suspend fun getPosts(key: String, type: String) =
        apiService.getPosts(
            "${Constants.BASE_URL}?key=$key&q=$type&image_type=photo&pretty=true"
        )
}