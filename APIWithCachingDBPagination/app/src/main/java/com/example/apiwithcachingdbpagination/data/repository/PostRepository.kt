package com.example.apiwithcachingdbpagination.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.apiwithcachingdbpagination.data.local.AppDatabase
import com.example.apiwithcachingdbpagination.data.local.PostEntity
import com.example.apiwithcachingdbpagination.data.remote.PostApi
import com.example.apiwithcachingdbpagination.data.remote.PostRemoteMediator
import kotlinx.coroutines.flow.Flow

class PostRepository(
    private val api: PostApi,
    private val database: AppDatabase,
    private val pageSize: Int = 20
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getPostsStream(): Flow<PagingData<PostEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            remoteMediator = PostRemoteMediator(
                api = api,
                database = database,
                pageSize = pageSize
            ),
            pagingSourceFactory = { database.postDao().pagingSource() }
        ).flow
    }
}

