package com.example.logginscreen.`interface`

import com.example.logginscreen.model.News
import com.example.logginscreen.model.WebSite
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsService {
    //https://newsapi.org/ can be deleted since already declared in BASE_URL
    @get:GET("v2/sources?apiKey=6edef381454a48c2bde9deeaf4271c25")
    val sources:Call<WebSite>

    @GET
    fun getNewsFromSource(@Url url:String):Call<News>
}