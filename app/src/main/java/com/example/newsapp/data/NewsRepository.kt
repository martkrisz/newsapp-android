package com.example.newsapp.data

import com.example.newsapp.api.NewsApi
import com.example.newsapp.db.NewsDao
import com.example.newsapp.models.NewsApiResponse
import retrofit2.Response

class NewsRepository(private val api : NewsApi, private val db: NewsDao) : BaseRepository() {

    suspend fun getTopHeadlines() : NewsApiResponse? {

        val result: Response<NewsApiResponse>
        val dbResult = db.getNews()
        return if (dbResult.isEmpty()) {
            result = api.getTopHeadlinesAsync().await()
            db.insertNews(result.body()!!.articles)
            safeApiCall(
                call = { result },
                errorMessage = "Error Fetching Top Headlines"
            );
        } else {
            NewsApiResponse(status = "ok", totalResult = dbResult.size, articles = dbResult)
        }

    }

}