package com.example.exmple1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exmple1.R
import com.example.exmple1.model.Article

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private val articles: MutableList<Article> = mutableListOf();

    class ViewHolder (
        val layout: LinearLayout,
        val feed: TextView,
        val title: TextView,
        val summary: TextView
    ) : RecyclerView.ViewHolder(layout)

    fun add(articles: List<Article>) {
        this.articles.addAll(articles);
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.article, parent, false) as LinearLayout
        val feed = layout.findViewById<TextView>(R.id.description);
        val title = layout.findViewById<TextView>(R.id.title);
        val summary = layout.findViewById<TextView>(R.id.summary);

        return ViewHolder(layout, feed, title, summary)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]

        holder.feed.text = article.feed
        holder.title.text = article.title
        holder.summary.text = article.summary
    }

    override fun getItemCount() = articles.size

}