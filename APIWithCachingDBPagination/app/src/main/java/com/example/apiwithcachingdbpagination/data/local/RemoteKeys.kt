package com.example.apiwithcachingdbpagination.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey val postId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)

