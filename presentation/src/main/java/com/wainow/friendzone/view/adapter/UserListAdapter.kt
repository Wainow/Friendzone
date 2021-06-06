package com.wainow.friendzone.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wainow.domain.entity.User

class UserListAdapter(private var userList: List<User>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserListViewHolder.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserListViewHolder).bindTo(userList[position], position)
    }

    override fun getItemCount() = userList.size

    fun addUsers(users: List<User>){
        userList = users
    }
}