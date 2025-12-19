package com.example.apiwithcachingdbpagination.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.apiwithcachingdbpagination.data.local.PostEntity

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PostListScreen(
    viewModel: PostListViewModel
) {
    val posts: LazyPagingItems<PostEntity> = viewModel.posts.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Posts Demo (Offline + Paging)") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = posts.loadState.refresh) {
                is LoadState.Loading -> LoadingState()
                is LoadState.Error -> ErrorState(
                    message = state.error.localizedMessage ?: "Something went wrong",
                    onRetry = { posts.retry() }
                )
                else -> PostList(
                    posts = posts,
                    contentPadding = PaddingValues(12.dp)
                )
            }
        }
    }
}

@Composable
private fun PostList(
    posts: LazyPagingItems<PostEntity>,
    contentPadding: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = posts.itemCount,
            key = { index -> posts[index]?.id ?: index }
        ) { index ->
            posts[index]?.let { post ->
                PostCard(post)
            }
        }

        when (val state = posts.loadState.append) {
            is LoadState.Loading -> item { ListLoading() }
            is LoadState.Error -> item {
                ErrorState(
                    message = state.error.localizedMessage ?: "Failed to load more",
                    onRetry = { posts.retry() }
                )
            }
            else -> Unit
        }
    }
}

@Composable
private fun PostCard(post: PostEntity) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = post.body,
                modifier = Modifier.padding(top = 6.dp),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "User ${post.userId} • Post ${post.id}",
                modifier = Modifier.padding(top = 6.dp),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ListLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message, style = MaterialTheme.typography.bodyMedium)
        Button(
            modifier = Modifier.padding(top = 12.dp),
            onClick = onRetry
        ) {
            Text(text = "Retry")
        }
    }
}

