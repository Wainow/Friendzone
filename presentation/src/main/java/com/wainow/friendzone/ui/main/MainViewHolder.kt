package com.wainow.friendzone.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mikhaellopez.circularimageview.CircularImageView
import com.wainow.domain.entity.User
import com.wainow.friendzone.R
import com.wainow.friendzone.utils.IdToColorMapper

class MainViewHolder(view: View): RecyclerView.ViewHolder(view) {
    companion object{
        fun create(parent: ViewGroup): MainViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
            return MainViewHolder(view)
        }
    }
    private val nameView: TextView = view.findViewById(R.id.name_tv)
    private val emailView: TextView = view.findViewById(R.id.email_tv)
    private val imageView: CircularImageView = view.findViewById(R.id.avatar_iv)
    private val isActiveView: ImageView = view.findViewById(R.id.active_iv)
    //private val emailView: TextView = view.findViewById(R.id.email_tv)
    //private val phoneView: TextView = view.findViewById(R.id.phone_tv)

    fun bindTo(
        user: User,
        index: Int
    ){
        with(user){
            nameView.text = name
            emailView.text = email
            try {
                imageView.circleColor =
                    Color.parseColor(
                        IdToColorMapper.map(id.toInt())
                        )
            } catch (e: IllegalArgumentException) {
                imageView.circleColor =
                    Color.parseColor(
                        "#ffffff"
                    )
            }
            isActiveView.visibility =
                if (isActive) View.VISIBLE
                else View.INVISIBLE
            //emailView.text = email
            //phoneView.text = phone
        }
    }
}