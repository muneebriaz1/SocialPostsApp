package com.appiskey.socialpostsapp.helper

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appiskey.socialpostsapp.data.api.ApiHelper
import com.appiskey.socialpostsapp.data.respository.MainActivityRepository
import com.appiskey.socialpostsapp.viewmodel.MainActivityViewModel


class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val application: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(
                MainActivityRepository(
                    apiHelper
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}