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
import kotlinx.coroutines.newSingleThreadContext

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val newDispatcher = newSingleThreadContext("ServiceCall")
        super.onCreate(savedInstanceState)
    }

    private val feeds = listOf(
        Feed("npr", "https://www.npr.org/rss/rss.php?id=1001"),
        Feed("cnn", "http://rss.cnn.com/rss/cnn_topstories.rss"),
        Feed("fox", "http://foxnews.com/foxnews/latest?format=xml"),
        Feed("inv", "htt:mynNewsFeed")
    )
}