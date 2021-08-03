package com.example.exmple1

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.exmple1.databinding.ActivityMainBinding
import com.example.exmple1.model.Feed
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import org.w3c.dom.Element
import org.w3c.dom.Node
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration;
    private lateinit var binding: ActivityMainBinding;
    private val newDispatcher = newSingleThreadContext("ServiceCall");
    private val factory = DocumentBuilderFactory.newInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(newDispatcher) {
            val headLine = fetchRssHeadLines();
        }
    }

    private val feeds = listOf(
        Feed("npr", "https://www.npr.org/rss/rss.php?id=1001"),
        Feed("cnn", "http://rss.cnn.com/rss/cnn_topstories.rss"),
        Feed("fox", "http://foxnews.com/foxnews/latest?format=xml"),
        Feed("inv", "htt:mynNewsFeed")
    );

    private fun fetchRssHeadLines(): List<String> {
        val builder = factory.newDocumentBuilder();
        val xml = builder.parse("https://www.npr.org/rss/rss.php?id=1001");
        val news = xml.getElementsByTagName("channel").item(0);
        return (0 until news.childNodes.length)
                .map {news.childNodes.item(it)}
                .filter { Node.ELEMENT_NODE == it.nodeType }
                .map { it as Element }
                .filter { "item" == it.tagName }
                .map { it.getElementsByTagName("title").item(0).textContent }
    }
}