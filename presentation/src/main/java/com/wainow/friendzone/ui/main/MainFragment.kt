package com.wainow.friendzone.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.wainow.data.api.ApiHelper
import com.wainow.data.api.RetrofitBuilder
import com.wainow.data.repository.UserRepositoryImpl
import com.wainow.domain.entity.User
import com.wainow.domain.interactor.UserInteractor
import com.wainow.domain.interactor.UserInteractorImpl
import com.wainow.domain.repository.UserRepository
import com.wainow.domain.utils.Status
import com.wainow.friendzone.R
import com.wainow.friendzone.ui.base.ViewModelFactory

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var layout: ConstraintLayout
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        initView(view)
        setupRecycler(recyclerView)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
        setupObservers()
        viewModel.getUsers()
    }

    private fun initView(view: View){
        recyclerView = view.findViewById(R.id.user_list_rv)
        layout = view.findViewById(R.id.main)
        progressBar = view.findViewById(R.id.loading_pb)
    }

    private fun setupRecycler(recyclerView: RecyclerView){
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MainAdapter(listOf())
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
                this,
                ViewModelFactory(
                        UserRepositoryImpl(
                                ApiHelper(RetrofitBuilder.apiService)
                        )
                )
        ).get(MainViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(viewLifecycleOwner, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        it.message?.let { text ->
                            Snackbar.make(layout, text, Snackbar.LENGTH_LONG).show()
                            Log.d("DebugLogs", text)
                        }
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {
        adapter.apply {
            addUsers(users)
            notifyDataSetChanged()
        }
    }

}