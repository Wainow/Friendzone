package com.wainow.friendzone.view.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wainow.domain.interactor.UserInteractorImpl
import com.wainow.friendzone.view.fragment.UserDetailsViewModel
import com.wainow.friendzone.view.fragment.UserListViewModel

class SavedStateVMFactory(private val state: SavedStateHandle): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel(state) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}