package com.turker.github_repository_paging3_sample.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.turker.github_repository_paging3_sample.R
import com.turker.github_repository_paging3_sample.data.model.RepoModel
import com.turker.github_repository_paging3_sample.databinding.FeatureColumnListItemBinding
import com.turker.github_repository_paging3_sample.databinding.ItemRepoListBinding


class RepoViewHolder(private val binding: FeatureColumnListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(item: RepoModel) {
        Log.e("apiWallpaper", "-->" + "True")
        Glide.with(itemView.context)
            .load(item.thumbnail_file)
            .thumbnail(0.15f) // Load a thumbnail 25% the size of the original image
            .into(binding.ivImage)

    }
}