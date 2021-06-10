package com.wainow.friendzone.view.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.Image
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.SavedStateHandle
import com.mikhaellopez.circularimageview.CircularImageView
import com.wainow.data.api.ApiHelper
import com.wainow.data.api.RetrofitBuilder
import com.wainow.data.repository.UserRepositoryImpl
import com.wainow.domain.entity.User
import com.wainow.friendzone.R
import com.wainow.friendzone.utils.FragementViewHelper
import com.wainow.friendzone.utils.RegisteredDateMapper
import com.wainow.friendzone.view.activity.MainActivity
import com.wainow.friendzone.view.base.SavedStateVMFactory
import com.wainow.friendzone.view.base.VMFactory
import org.w3c.dom.Text
import java.nio.InvalidMarkException

class UserDetailsFragment() : Fragment() {
    constructor(user: User) : this() {
        currentUser = user
    }
    companion object {
        fun newInstance(user: User) = UserDetailsFragment(user)
    }
    private var currentUser: User? = null
    private lateinit var viewModel: UserDetailsViewModel
    private lateinit var ageView: TextView
    private lateinit var companyView: TextView
    private lateinit var emailView: TextView
    private lateinit var phoneView: TextView
    private lateinit var addressView: TextView
    private lateinit var aboutView: TextView
    private lateinit var registeredView: TextView
    private lateinit var coordinatesView: TextView
    private lateinit var eyeView: CircularImageView
    private lateinit var emojiView: TextView

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
    private fun initView(view: View) {
        ageView = view.findViewById(R.id.age_tv)
        companyView = view.findViewById(R.id.company_tv)
        emailView = view.findViewById(R.id.email_tv)
        phoneView = view.findViewById(R.id.phone_tv)
        addressView = view.findViewById(R.id.address_tv)
        aboutView = view.findViewById(R.id.about_tv)
        registeredView = view.findViewById(R.id.registered_date_tv)
        coordinatesView = view.findViewById(R.id.coordinates_tv)
        eyeView = view.findViewById(R.id.eye_iv)
        emojiView = view.findViewById(R.id.emoji_iv)
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
    private fun setupView(user: User){
        setupTextView(user)
        FragementViewHelper.setupEyeColorView(user, eyeView)
        FragementViewHelper.setupEmojiView(user, emojiView)
        setupFriendListView()
    }
    @SuppressLint("SetTextI18n")
    private fun setupTextView(user: User){
        with(user){
            (activity as? MainActivity)?.toolbar?.title = name
            ageView.text = "$age years old"
            companyView.text = company
            emailView.text = email
            phoneView.text = phone
            addressView.text = address
            aboutView.text = about
            registeredView.text = RegisteredDateMapper.map(registered)
            coordinatesView.text = "$longitude:$latitude"
        }
    }
    private fun setupFriendListView(){
        activity
                ?.supportFragmentManager
                ?.fragments?.get(0)
                ?.childFragmentManager
                ?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.user_friends_container, UserListFragment.newInstance())
                ?.commit()
    }
}