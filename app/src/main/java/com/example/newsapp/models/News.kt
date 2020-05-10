package com.example.newsapp.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "news")
data class News (
    @Embedded
    var source: Source? = null,
    @ColumnInfo(name = "author")
    var author: kotlin.String? = null,
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "title")
    var title: kotlin.String,
    @ColumnInfo(name = "description")
    var description: kotlin.String? = null,
    @ColumnInfo(name = "url")
    var url: kotlin.String? = null,
    @ColumnInfo(name = "urlToImage")
    var urlToImage: kotlin.String? = null,
    @ColumnInfo(name = "publishedAt")
    var publishedAt: kotlin.String? = null,
    @ColumnInfo(name = "content")
    var content: kotlin.String? = null
) {

}

