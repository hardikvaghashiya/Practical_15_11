package com.practicaltest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicaltest.model.DocumentResponseModel
import com.practicaltest.repository.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val movieList = MutableLiveData<DocumentResponseModel>()
    val errorMessage = MutableLiveData<String>()

    fun getAllDocuments() {
        val response = repository.getDocuments()
        response.enqueue(object : Callback<DocumentResponseModel> {
            override fun onResponse(call: Call<DocumentResponseModel>, response: Response<DocumentResponseModel>) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<DocumentResponseModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}