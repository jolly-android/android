package com.example.apiwithcachingdbpagination.di

import android.content.Context
import androidx.room.Room
import com.example.apiwithcachingdbpagination.data.local.AppDatabase
import com.example.apiwithcachingdbpagination.data.remote.PostApi
import com.example.apiwithcachingdbpagination.data.repository.PostRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AppContainer(context: Context) {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val database: AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "posts.db"
    ).build()

    private val api: PostApi = retrofit.create(PostApi::class.java)

    val repository: PostRepository = PostRepository(
        api = api,
        database = database
    )
}

