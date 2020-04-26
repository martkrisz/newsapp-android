package com.example.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.newsapp.viewModel.NewsViewModel
import com.example.newsapp.R


class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)

        newsViewModel.fetchNews()

        newsViewModel.topHeadlinesLiveData.observe(this, Observer {

            //TODO
        })
    }


}
