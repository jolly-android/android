package com.example.interviewpractiseapicall.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewpractiseapicall.databinding.ItemRecyclerPostBinding
import com.example.interviewpractiseapicall.model.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users: List<User> = emptyList()

    fun submitList(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }
    class UserViewHolder(private val binding:ItemRecyclerPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.title.text = user.title
            Log.d("User title", user.title.toString())
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = ItemRecyclerPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }



}