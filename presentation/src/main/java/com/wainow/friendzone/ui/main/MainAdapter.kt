package com.wainow.friendzone.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wainow.domain.entity.User

class MainAdapter(private var userList: List<User>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MainViewHolder).bindTo(userList[position], position)
    }

    override fun getItemCount() = userList.size

    fun addUsers(users: List<User>){
        userList = users
    }
}