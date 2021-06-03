package com.wainow.friendzone.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wainow.domain.interactor.UserInteractor
import com.wainow.domain.interactor.UserInteractorImpl
import com.wainow.domain.repository.UserRepository
import com.wainow.friendzone.ui.main.MainViewModel

class ViewModelFactory(private val userRepository: UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(UserInteractorImpl(userRepository)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}