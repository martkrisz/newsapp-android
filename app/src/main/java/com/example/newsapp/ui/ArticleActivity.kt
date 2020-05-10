package com.example.newsapp.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.newsapp.R
import com.example.newsapp.models.News
import com.example.newsapp.models.Source
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_article.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ArticleActivity : AppCompatActivity() {

    private var article: News = News(title="")
    private lateinit var layout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        article.urlToImage = intent.getStringExtra("urlToImage")
        article.content = intent.getStringExtra("content")
        article.author = intent.getStringExtra("author")
        article.source = Source(id="", name = intent.getStringExtra("source"))
        article.title = intent.getStringExtra("title")
        val parsedDate = LocalDateTime.parse(intent.getStringExtra("publishedAt"), DateTimeFormatter.ISO_DATE_TIME)
        val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))

        article.publishedAt = formattedDate

        setContentView(R.layout.activity_article)
        toolbar.title = article.title
        setSupportActionBar(toolbar)
        fab.setOnClickListener {
            val openUrl = Intent(android.content.Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(intent.getStringExtra("url"))
            startActivity(openUrl)
        }

        layout = findViewById(R.id.article_layout)

        val authorTextView = layout.findViewById(R.id.article_author) as TextView
        val sourceTextView = layout.findViewById(R.id.article_source) as TextView
        val publishedAtTextView = layout.findViewById(R.id.article_publishedAt) as TextView
        val contentTextView = layout.findViewById(R.id.article_content) as TextView
        val imageView = layout.findViewById(R.id.article_image) as ImageView

        authorTextView.text = article.author
        sourceTextView.text = article.source!!.name
        publishedAtTextView.text = article.publishedAt
        contentTextView.text = article.content

        Picasso.get().load(article.urlToImage).placeholder(R.mipmap.ic_launcher).into(imageView)
    }

    companion object {

        fun newIntent(context: Context, article: News): Intent {
            val detailIntent = Intent(context, ArticleActivity::class.java)

            detailIntent.putExtra("title", article.title)
            detailIntent.putExtra("url", article.url)
            detailIntent.putExtra("urlToImage", article.urlToImage)
            detailIntent.putExtra("source", article.source!!.name)
            detailIntent.putExtra("author", article.author)
            detailIntent.putExtra("publishedAt", article.publishedAt)
            detailIntent.putExtra("content", article.content)

            return detailIntent
        }
    }
}
