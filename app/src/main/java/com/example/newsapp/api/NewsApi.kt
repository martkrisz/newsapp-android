package com.example.newsapp.api
import com.example.newsapp.models.NewsApiResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("top-headlines")
    fun getTopHeadlinesAsync(): Deferred<Response<NewsApiResponse>>
}