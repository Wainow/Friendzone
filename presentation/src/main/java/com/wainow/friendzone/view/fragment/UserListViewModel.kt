package com.wainow.friendzone.view.fragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.wainow.domain.entity.User
import com.wainow.domain.interactor.UserInteractor
import com.wainow.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class UserListViewModel(private val userInteractor: UserInteractor) : ViewModel() {

    fun getUsers() = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null))
        try {
            var data: List<User> = userInteractor.getLocalUsers()
            if(data.isEmpty()){
                data = userInteractor.getUsers()
                userInteractor.saveUsers(data)
            }
            emit(Resource.success(data = data))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun updateUsers(){
        runBlocking { withContext(Dispatchers.IO) { userInteractor.deleteUsers() } }
        getUsers()
    }
}