package com.example.exmple1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exmple1.adapter.ArticleAdapter
import com.example.exmple1.databinding.ActivityMainBinding
import com.example.exmple1.model.Article
import com.example.exmple1.model.Feed
import kotlinx.coroutines.*
import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {

    private val factory = DocumentBuilderFactory.newInstance();
    private lateinit var articles: RecyclerView
    private lateinit var viewAdapter: ArticleAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        viewManager = LinearLayoutManager(this);
        viewAdapter = ArticleAdapter()
        articles = findViewById<RecyclerView>(R.id.articles).apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        asyncLoadNews();
    }


    private val feeds = listOf(
        Feed("npr", "https://www.npr.org/rss/rss.php?id=1001"),
    );

    private fun asyncFetchHeadLines(feed: Feed, dispatcher: CoroutineDispatcher) = GlobalScope.async(dispatcher) {
        val builder = factory.newDocumentBuilder();
        val xml = builder.parse(feed.url);
        val news = xml.getElementsByTagName("channel").item(0);

        (0 until news.childNodes.length)
        .map {news.childNodes.item(it)}
        .filter { Node.ELEMENT_NODE == it.nodeType }
        .map { it as Element }
        .filter { "item" == it.tagName }
        .map { it.getElementsByTagName("title").item(0).textContent }
    }

    private fun asyncFetchArticles(feed: Feed, dispatcher: CoroutineDispatcher) = GlobalScope.async(dispatcher) {
        val builder = factory.newDocumentBuilder();
        val xml = builder.parse(feed.url);
        val news = xml.getElementsByTagName("channel").item(0);

        (0 until news.childNodes.length)
            .map {news.childNodes.item(it)}
            .filter { Node.ELEMENT_NODE == it.nodeType }
            .map { it as Element }
            .filter { "item" == it.tagName }
            .map {
                val title = it.getElementsByTagName("title").item(0).textContent
                var summary = it.getElementsByTagName("description").item(0).textContent

                if(!summary.startsWith("<div") && summary.contains("<div")) {
                    summary = summary.substring(0, summary.indexOf("<div"))
                }

                Article(feed.name, title, summary)
            }
    }

    private fun asyncLoadNews() = GlobalScope.launch {
        val request = mutableListOf<Deferred<List<Article>>>()
        val dispatcher = newFixedThreadPoolContext(2, "IO");
        feeds.mapTo(request) {
            asyncFetchArticles(it, dispatcher);
        }
        request.forEach {
            it.join();
        }

        val articles = request
            .filter { !it.isCancelled; }
            .flatMap { it.getCompleted(); }

        val failCount = request
            .filter { it.isCancelled; }

        val obtained = request.size - failCount.size

        val newCount = findViewById<TextView>(R.id.newsCount);
        GlobalScope.launch(Dispatchers.Main) {
            viewAdapter.add(articles)
        }
    }
}