package com.example.logginscreen.common

import com.example.logginscreen.`interface`.NewsService
import com.example.logginscreen.remote.RetrofitClient

object Common{
    val BASE_URL = "https://newsapi.org/"
    val API_KEY = "6edef381454a48c2bde9deeaf4271c25"

    val newsService: NewsService
        get() = RetrofitClient.getClient(BASE_URL).create(NewsService::class.java)
}