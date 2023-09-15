package com.appiskey.socialpostsapp.data.respository


import com.appiskey.socialpostsapp.data.api.ApiHelper

class MainActivityRepository(
    private val apiHelper: ApiHelper,
) {

    //-------------------------------------------------------------api calls--------------------------------------------------------------------------

    suspend fun getPosts(key: String, type: String) = apiHelper.getPosts(key, type)

}