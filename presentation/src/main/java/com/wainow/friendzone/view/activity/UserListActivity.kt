package com.wainow.friendzone.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wainow.friendzone.R
import com.wainow.friendzone.view.fragment.UserListFragment

class UserListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_list_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, UserListFragment.newInstance())
                    .commitNow()
        }
    }
}