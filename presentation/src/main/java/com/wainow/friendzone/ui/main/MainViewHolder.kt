package com.wainow.friendzone.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wainow.domain.entity.User
import com.wainow.friendzone.R

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
    companion object{
        fun create(parent: ViewGroup, ): MainViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
            return MainViewHolder(view)
        }
    }
    private val nameView: TextView = view.findViewById(R.id.name_tv)
    private val emailView: TextView = view.findViewById(R.id.email_tv)
    private val phoneView: TextView = view.findViewById(R.id.phone_tv)

    fun bindTo(
        user: User,
        index: Int
    ){
        with(user){
            nameView.text = name
            emailView.text = email
            phoneView.text = phone
        }
    }
}