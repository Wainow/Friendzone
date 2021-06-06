package com.wainow.friendzone.view.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.SavedStateHandle
import com.wainow.data.api.ApiHelper
import com.wainow.data.api.RetrofitBuilder
import com.wainow.data.repository.UserRepositoryImpl
import com.wainow.domain.entity.User
import com.wainow.friendzone.R
import com.wainow.friendzone.view.base.SavedStateVMFactory
import com.wainow.friendzone.view.base.VMFactory

class UserDetailsFragment() : Fragment() {

    constructor(user: User) : this() {
        currentUser = user
    }

    companion object {
        fun newInstance(user: User) = UserDetailsFragment(user)
    }

    private var currentUser: User? = null
    private lateinit var viewModel: UserDetailsViewModel
    private lateinit var nameView: TextView
    private lateinit var ageView: TextView
    private lateinit var companyView: TextView
    private lateinit var emailView: TextView
    private lateinit var phoneView: TextView
    private lateinit var addressView: TextView
    private lateinit var aboutView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_details_fragment, container, false)
        initView(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViewModel()
    }

    private fun initView(view: View){
        nameView = view.findViewById(R.id.name_tv)
        ageView = view.findViewById(R.id.age_tv)
        companyView = view.findViewById(R.id.company_tv)
        emailView = view.findViewById(R.id.email_tv)
        phoneView = view.findViewById(R.id.phone_tv)
        addressView = view.findViewById(R.id.address_tv)
        aboutView = view.findViewById(R.id.about_tv)
    }

    private fun setupView(user: User){
        with(user){
            nameView.text = name
            ageView.text = age.toString()
            companyView.text = company
            emailView.text = email
            phoneView.text = phone
            addressView.text = address
            aboutView.text = about
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            SavedStateVMFactory(
                SavedStateHandle()
            )
        ).get(UserDetailsViewModel::class.java)
        currentUser?.let { viewModel.saveCurrentUser(it) }
        viewModel.getCurrentUser()?.let { setupView(it) }
    }

}