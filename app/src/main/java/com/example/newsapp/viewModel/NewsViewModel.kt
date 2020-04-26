package com.example.newsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.Injection
import com.example.newsapp.api.ApiFactory
import com.example.newsapp.models.NewsApiResponse
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.db.NewsDao
import com.example.newsapp.db.NewsDatabase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class NewsViewModel(private val db: NewsDao) : ViewModel(){

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository : NewsRepository =
        NewsRepository(ApiFactory.newsApi, db)


    val topHeadlinesLiveData = MutableLiveData<NewsApiResponse>()

    fun fetchNews(){
        scope.launch {
            val topHeadlines = repository.getTopHeadlines()
            topHeadlinesLiveData.postValue(topHeadlines)
        }
    }


    fun cancelAllRequests() = coroutineContext.cancel()

}