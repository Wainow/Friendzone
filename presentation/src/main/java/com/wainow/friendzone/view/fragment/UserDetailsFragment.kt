package com.wainow.friendzone.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.mikhaellopez.circularimageview.CircularImageView
import com.wainow.domain.entity.Friend
import com.wainow.domain.entity.User
import com.wainow.friendzone.R
import com.wainow.friendzone.utils.FragementViewHelper
import com.wainow.friendzone.utils.RegisteredDateMapper
import com.wainow.friendzone.view.activity.MainActivity
import com.wainow.friendzone.view.base.SavedStateVMFactory


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
    private lateinit var emailImageView: ImageView
    private lateinit var phoneImageView: ImageView
    private lateinit var coordinatesImageView: ImageView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_details_fragment, container, false)
        initView(view)
        setupToolbarArrows()
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
        emailImageView = view.findViewById(R.id.email_iv)
        phoneImageView = view.findViewById(R.id.phone_iv)
        coordinatesImageView = view.findViewById(R.id.coordinates_iv)
    }
    private fun setupToolbarArrows(){
        with(activity as MainActivity){
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
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
    private fun setupView(user: User){
        setupTextView(user)
        FragementViewHelper.setupEyeColorView(user, eyeView)
        FragementViewHelper.setupEmojiView(user, emojiView)
        setupFriendListView(user.friends)
        setupViewClickListeners(
                user = user,
                emailIntent = {view, email -> composeEmailIntent(view, email) },
                phoneIntent = {view, phone -> callPhoneIntent(view, phone) },
                mapIntent = {view, l1, l2 -> findMapIntent(view, l1, l2)}
        )
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
            coordinatesView.text = "$latitude,$longitude"
        }
    }
    private fun setupFriendListView(friends: List<Friend>){
        activity
                ?.supportFragmentManager
                ?.fragments?.get(0)
                ?.childFragmentManager
                ?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.replace(R.id.user_friends_container, UserListFragment.newInstance(friends))
                ?.commit()
    }
    private fun setupViewClickListeners(
            user: User,
            emailIntent: (View, String) -> Unit,
            phoneIntent: (View, String) -> Unit,
            mapIntent: (View, Double, Double) -> Unit
    ){
        with(user){
            emailView.setOnClickListener { emailIntent(it, email) }
            emailImageView.setOnClickListener { emailIntent(it, email) }
            phoneView.setOnClickListener { phoneIntent(it, phone) }
            phoneImageView.setOnClickListener { phoneIntent(it, phone) }
            coordinatesView.setOnClickListener { mapIntent(it, latitude, longitude) }
            coordinatesImageView.setOnClickListener { mapIntent(it, latitude, longitude) }
        }
    }
    private fun composeEmailIntent(view: View, email: String) {
        try{
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
            }
        startActivity(Intent.createChooser(emailIntent, ""))
        } catch (e: android.content.ActivityNotFoundException){
            e.printStackTrace()
            Snackbar.make(view, R.string.implicit_intent_error, Snackbar.LENGTH_SHORT)
                    .show()
        }
    }
    private fun callPhoneIntent(view: View, phone: String){
        try {
            val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel: $phone")
            }
            startActivity(phoneIntent)
        } catch (e: android.content.ActivityNotFoundException){
            e.printStackTrace()
            Snackbar.make(view, R.string.implicit_intent_error, Snackbar.LENGTH_SHORT)
                    .show()
        }
    }
    private fun findMapIntent(view: View, latitude: Double, longitude: Double){
        try{
            val mapIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:$latitude,$longitude")
            }
            startActivity(mapIntent)
        } catch (e: android.content.ActivityNotFoundException){
            e.printStackTrace()
            Snackbar.make(view, R.string.implicit_intent_error, Snackbar.LENGTH_SHORT)
                    .show()
        }
    }
}