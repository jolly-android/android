package com.example.apiwithcachingdbpagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.apiwithcachingdbpagination.ui.theme.APIWithCachingDBPaginationTheme
import com.example.apiwithcachingdbpagination.di.AppContainer
import com.example.apiwithcachingdbpagination.ui.PostListScreen
import com.example.apiwithcachingdbpagination.ui.PostListViewModel
import com.example.apiwithcachingdbpagination.ui.PostListViewModelFactory

class MainActivity : ComponentActivity() {

    private val appContainer by lazy { AppContainer(this) }

    private val viewModel: PostListViewModel by viewModels {
        PostListViewModelFactory(appContainer.repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APIWithCachingDBPaginationTheme {
                PostListScreen(viewModel = viewModel)
            }
        }
    }
}