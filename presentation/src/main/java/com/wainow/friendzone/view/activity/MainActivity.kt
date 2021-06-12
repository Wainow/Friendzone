package com.wainow.friendzone.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.wainow.data.api.ApiHelper
import com.wainow.data.api.ApiService
import com.wainow.data.api.RetrofitBuilder
import com.wainow.data.repository.UserRepositoryImpl
import com.wainow.domain.interactor.UserInteractor
import com.wainow.domain.interactor.UserInteractorImpl
import com.wainow.domain.repository.UserRepository
import com.wainow.friendzone.R
import com.wainow.friendzone.view.fragment.UserListFragment
import com.wainow.friendzone.view.fragment.UserListViewModel
import io.realm.Realm
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

val myModule = module {
    single { ApiHelper(RetrofitBuilder.apiService) }
    single { UserRepositoryImpl(get()) as UserRepository }
    single { UserInteractorImpl(get()) as UserInteractor }
    viewModel { UserListViewModel(get()) }
}

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var toolbar: Toolbar
    lateinit var swipeLayout: SwipeRefreshLayout
    companion object{
        const val USER_LIST_TITLE = "Friendzone"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_list_activity)
        Realm.init(this)
        startKoin {
            modules(myModule)
        }
        initView()
        setSupportActionBar(toolbar)
        setupToolbar()
        swipeLayout.setOnRefreshListener(this)
        if (savedInstanceState == null) {
            openContactList()
        }
    }
    private fun initView(){
        toolbar = findViewById(R.id.toolbar)
        swipeLayout = findViewById(R.id.swipe_layout)
    }
    private fun setupToolbar(){
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
        if(supportFragmentManager.backStackEntryCount == 1){
            with(supportActionBar){
                if(this != null){
                    title = USER_LIST_TITLE
                    setDisplayHomeAsUpEnabled(false)
                    setDisplayShowHomeEnabled(false)
                }
            }
        } else if(supportFragmentManager.backStackEntryCount == 0)
            super.onBackPressed()
    }

    override fun onRefresh() {
        val viewModel by viewModel<UserListViewModel>()
        viewModel.updateUsers()
        (supportFragmentManager.fragments[0] as? UserListFragment)?.setupObservers()
        viewModel.getUsers()
        swipeLayout.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}