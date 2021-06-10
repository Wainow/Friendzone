package com.wainow.friendzone.view.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mikhaellopez.circularimageview.CircularImageView
import com.wainow.domain.entity.User
import com.wainow.friendzone.R
import com.wainow.friendzone.utils.FragementViewHelper
import com.wainow.friendzone.utils.FragmentTagHelper
import com.wainow.friendzone.utils.IdColorMapper
import com.wainow.friendzone.view.fragment.UserDetailsFragment

class UserListViewHolder(view: View): RecyclerView.ViewHolder(view) {
    companion object{
        fun create(parent: ViewGroup): UserListViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_list_item, parent, false)
            return UserListViewHolder(view)
        }
    }
    private val nameView: TextView = view.findViewById(R.id.name_tv)
    private val emailView: TextView = view.findViewById(R.id.email_tv)
    private val imageView: CircularImageView = view.findViewById(R.id.avatar_iv)
    private val isActiveView: CircularImageView = view.findViewById(R.id.active_iv)
    private val userView: CardView = view.findViewById(R.id.user_item_cv)
    fun bindTo(
        user: User,
        index: Int
    ){
        with(user){
            nameView.text = name
            emailView.text = email
            isActiveView.visibility =
                if (isActive) View.VISIBLE
                else View.INVISIBLE
            setBackgroundCircleImageView(imageView, id.toInt())
            itemView.setOnClickListener { openContactDetails(this) }
        }
        FragementViewHelper.setupEyeColorView(user, isActiveView)
    }

    private fun setBackgroundCircleImageView(imageView: CircularImageView, id: Int){
        try {
            imageView.circleColor =
                Color.parseColor(
                    IdColorMapper.map(id)
                )
        } catch (e: IllegalArgumentException) {
            imageView.circleColor =
                Color.parseColor(
                    "#ffffff"
                )
        }
    }

    private fun openContactDetails(user: User){
        if(user.isActive) {
            val activity = (itemView.context as AppCompatActivity)
            val manager = activity.supportFragmentManager
            val fragmentTag = FragmentTagHelper.getFragmentTag(manager)
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            activity.supportActionBar?.setDisplayShowHomeEnabled(true)
            manager
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.container, UserDetailsFragment.newInstance(user), fragmentTag)
                    .addToBackStack(null)
                    .commit()
            Log.d("DebugLogs", "Added fragment with tag: $fragmentTag")
        }
    }
}