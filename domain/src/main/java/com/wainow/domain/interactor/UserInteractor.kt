package com.wainow.domain.interactor

import com.wainow.domain.entity.User

interface UserInteractor {
    suspend fun getUsers(): List<User>
    fun saveUsers(users: List<User>)
    fun getLocalUsers(): List<User>
    fun deleteUsers()
}