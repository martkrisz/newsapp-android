package com.example.newsapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.models.News
import io.reactivex.Completable

import io.reactivex.Flowable

@Dao
interface NewsDao {
    @Query("SELECT * FROM News")
    fun getNews(): List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<News>): Completable
}