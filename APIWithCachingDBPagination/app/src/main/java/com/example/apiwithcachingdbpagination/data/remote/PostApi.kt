package com.example.apiwithcachingdbpagination.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Simple API definition against jsonplaceholder for demo purposes.
 */
interface PostApi {
    @GET("posts")
    suspend fun getPosts(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): List<PostDto>
}

data class PostDto(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)

