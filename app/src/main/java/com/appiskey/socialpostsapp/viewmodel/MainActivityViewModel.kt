package com.appiskey.socialpostsapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.appiskey.socialpostsapp.data.respository.MainActivityRepository
import com.appiskey.socialpostsapp.utils.Resource
import kotlinx.coroutines.Dispatchers


class MainActivityViewModel(
    private val mainActivityRepository: MainActivityRepository,
) : ViewModel() {

    //    ---------------------------------------------------------- API calls --------------------------------------------------------------------

    fun getPosts(key: String, type: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try{
            emit(Resource.success(data = mainActivityRepository.getPosts(key, type)))
        }catch (e: Exception){
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}