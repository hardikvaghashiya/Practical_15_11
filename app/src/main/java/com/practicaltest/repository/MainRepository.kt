package com.practicaltest.repository

import com.practicaltest.retrofit.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {
    fun getDocuments() = retrofitService.getDocuments()
}