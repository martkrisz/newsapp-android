package com.example.newsapp.api

import com.example.newsapp.newsApiKey
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiFactory {

    val interceptor = HttpLoggingInterceptor()

    private val authInterceptor = Interceptor {chain->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("country", "us")
            .addQueryParameter("apiKey", newsApiKey)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        chain.proceed(newRequest)
    }

    private val newsApiClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(interceptor)
        .build()



    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(newsApiClient)
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()


    val newsApi : NewsApi = retrofit().create(NewsApi::class.java)

}