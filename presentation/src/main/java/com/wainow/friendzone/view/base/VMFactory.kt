package com.wainow.friendzone.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wainow.domain.interactor.UserInteractorImpl
import com.wainow.domain.repository.UserRepository
import com.wainow.friendzone.view.fragment.UserListViewModel

class VMFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            return UserListViewModel(UserInteractorImpl(userRepository)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}