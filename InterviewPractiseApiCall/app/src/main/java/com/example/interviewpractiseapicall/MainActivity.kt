package com.example.interviewpractiseapicall

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewpractiseapicall.adapter.UserAdapter
import com.example.interviewpractiseapicall.databinding.ActivityMainBinding
import com.example.interviewpractiseapicall.model.User
import com.example.interviewpractiseapicall.viewModel.UsersViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: UsersViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    var adapter = UserAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        observerUsers()
        viewModel.loadUsers()

    }

    fun observerUsers() {
        viewModel.users.observe(this) { users ->
           // println(users)
            //Log.d(" Users", users.toString())
            adapter.submitList(users)
        }
    }

    fun setAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}
