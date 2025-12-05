package com.turker.github_repository_paging3_sample.network

import com.turker.github_repository_paging3_sample.data.model.RepoModel
import com.turker.github_repository_paging3_sample.data.model.RepoResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface RepoService{
    @GET("items")
    suspend fun getItems(
        @Query("page") page: String,
        @Query("type") type: String="WALLPAPER",
        @Query("itemsPerPage") itemsPerPage: String = "10"
    ): ArrayList<RepoModel>
}