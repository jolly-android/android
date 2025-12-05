package com.turker.github_repository_paging3_sample.data.model

import com.google.gson.annotations.SerializedName

data class RepoModel(
    val countDownloads: Int? = null,
    val file: String? = null,
    val name: String? = null,
    val image: String? = null,
    val thumbnail_file: String? = null,
    val id: Int? = null,
    val type: String? = null,
    val user: User? = null,
    val zoomed_file: String? = null,
    val tags: List<TagsItem?>? = null
)


data class TagsItem(
    val name: String? = null,
    val id: Int? = null
)

data class User(
    val firstName: String? = null,
    val lastName: String? = null,
    val image: String? = null,
    @Transient var imageDrawable: Int? = 0,
    val id: Int? = null
)
