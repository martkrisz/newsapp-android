package com.example.newsapp.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "news")
data class News (
    @Embedded
    val source: Source? = null,
    @ColumnInfo(name = "author")
    val author: kotlin.String? = null,
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "title")
    val title: kotlin.String,
    @ColumnInfo(name = "description")
    val description: kotlin.String? = null,
    @ColumnInfo(name = "url")
    val url: kotlin.String? = null,
    @ColumnInfo(name = "urlToImage")
    val urlToImage: kotlin.String? = null,
    @ColumnInfo(name = "publishedAt")
    val publishedAt: kotlin.String? = null,
    @ColumnInfo(name = "content")
    val content: kotlin.String? = null
) {

}

