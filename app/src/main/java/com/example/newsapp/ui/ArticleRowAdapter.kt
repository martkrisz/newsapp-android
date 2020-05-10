package com.example.newsapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.newsapp.R
import com.example.newsapp.models.News
import com.squareup.picasso.Picasso

class ArticleRowAdapter(private val context: Context, private val articles: List<News>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return articles.size
    }

    override fun getItem(position: Int): Any {
        return articles[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView = inflater.inflate(R.layout.list_item_article, parent, false)

        val titleTextView = rowView.findViewById(R.id.article_list_title) as TextView
        val descriptionTextView = rowView.findViewById(R.id.article_list_description) as TextView
        val authorTextView = rowView.findViewById(R.id.article_list_author) as TextView
        val sourceTextView = rowView.findViewById(R.id.article_list_source) as TextView
        val imageView = rowView.findViewById(R.id.article_list_image) as ImageView

        val article = getItem(position) as News
        titleTextView.text = article.title
        descriptionTextView.text = article.description
        authorTextView.text = article.author
        sourceTextView.text = article.source!!.name

        Picasso.get().load(article.urlToImage).placeholder(R.mipmap.ic_launcher).into(imageView)

        return rowView
    }
}