package com.example.newsapp

import android.content.Context
import com.example.newsapp.db.NewsDao
import com.example.newsapp.db.NewsDatabase


object Injection {
    fun provideNewsDataSource(context: Context): NewsDao {
        val database = NewsDatabase.getInstance(context)
        return database.newsDao()
    }
}