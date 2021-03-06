package com.example.newsapp.ui

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsapp.viewModel.NewsViewModel
import com.example.newsapp.R
import com.example.newsapp.db.NewsDatabase
import com.example.newsapp.models.News


class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var listView: ListView
    private lateinit var articles: List<News>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = NewsDatabase.getInstance(this)
        listView = findViewById(R.id.article_list_view)

        newsViewModel = NewsViewModel(db.newsDao())

        newsViewModel.fetchNews()

        newsViewModel.topHeadlinesLiveData.observe(this, Observer {
            this.listView.adapter = ArticleRowAdapter(this, it.articles)
            this.articles = it.articles
        })

        listView.setOnItemClickListener{_,_, position, _ ->
            val selectedArticle = articles[position]

            val articleDetailIntent = ArticleActivity.newIntent(this, selectedArticle)
            startActivity(articleDetailIntent)
        }

        val crashButton = Button(this)
        crashButton.text = "Crash!"
        crashButton.setOnClickListener {
            throw RuntimeException("Test Crash") // Force a crash
        }

        addContentView(crashButton, ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT))

    }


}
