package com.example.apiwithcachingdbpagination.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.apiwithcachingdbpagination.data.local.PostEntity
import com.example.apiwithcachingdbpagination.data.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class PostListViewModel(
    repository: PostRepository
) : ViewModel() {

    val posts: Flow<PagingData<PostEntity>> = repository
        .getPostsStream()
        .cachedIn(viewModelScope)
}

class PostListViewModelFactory(
    private val repository: PostRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            return PostListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

