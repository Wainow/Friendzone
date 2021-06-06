package com.wainow.friendzone.view.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wainow.domain.interactor.UserInteractor
import com.wainow.domain.utils.Resource
import kotlinx.coroutines.Dispatchers

class UserListViewModel(private val userInteractor: UserInteractor) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = userInteractor.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}