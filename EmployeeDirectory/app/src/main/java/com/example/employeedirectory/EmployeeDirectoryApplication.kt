package com.example.employeedirectory

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger

/**
 * Application class for configuring global application settings
 */
class EmployeeDirectoryApplication : Application(), ImageLoaderFactory {
    
    /**
     * Configure Coil image loader with disk caching
     * Images are cached on disk to avoid wasting bandwidth
     */
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25) // Use 25% of app memory for image caching
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.02) // Use 2% of disk space
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED) // Enable disk caching
            .memoryCachePolicy(CachePolicy.ENABLED) // Enable memory caching
            .logger(DebugLogger()) // Enable logging for debugging
            .respectCacheHeaders(false) // Don't rely on HTTP caching
            .build()
    }
}

