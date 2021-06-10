package com.wainow.friendzone.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.wainow.friendzone.R
import com.wainow.friendzone.view.fragment.UserListFragment

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    companion object{
        const val USER_LIST_TITLE = "Friendzone"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_list_activity)
        initView()
        setSupportActionBar(toolbar)
        setupToolbar()
        if (savedInstanceState == null) {
            openContactList()
        }
    }
    private fun initView(){
        toolbar = findViewById(R.id.toolbar)
    }
    private fun setupToolbar(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = USER_LIST_TITLE
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
    private fun openContactList(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, UserListFragment.newInstance())
            .commitNow()
    }
    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
    }
}