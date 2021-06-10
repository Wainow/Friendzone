package com.wainow.friendzone.utils

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.mikhaellopez.circularimageview.CircularImageView
import com.wainow.domain.entity.User
import com.wainow.friendzone.R

class FragementViewHelper {
    companion object{
        fun setupEyeColorView(user: User, eyeView: CircularImageView){
            with(eyeView){
                when(user.eyeColor){
                    "brown" -> {borderColor = ContextCompat.getColor(context, R.color.brown)}
                    "green" -> {borderColor = ContextCompat.getColor(context, R.color.green)}
                    "blue" -> {borderColor = ContextCompat.getColor(context, R.color.blue)}
                    else -> {visibility = View.INVISIBLE}
                }
            }
        }
        fun setupEmojiView(user: User, emojiView: TextView){
            with(emojiView){
                when(user.favoriteFruit){
                    "banana" -> {text = resources.getString(R.string.banana)}
                    "strawberry" -> {text = resources.getString(R.string.strawberry)}
                    "apple" -> {text = resources.getString(R.string.apple)}
                    else -> {visibility = View.INVISIBLE}
                }
            }
        }
    }
}