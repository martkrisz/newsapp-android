package com.example.newsapp.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsapp.viewModel.NewsViewModel
import com.example.newsapp.R
import com.example.newsapp.db.NewsDatabase


class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = NewsDatabase.getInstance(this)
        listView = findViewById<ListView>(R.id.article_list_view)

        newsViewModel = NewsViewModel(db.newsDao())

        newsViewModel.fetchNews()

        newsViewModel.topHeadlinesLiveData.observe(this, Observer {
            this.listView.adapter = ArticleRowAdapter(this, it.articles)
        })
    }


}
