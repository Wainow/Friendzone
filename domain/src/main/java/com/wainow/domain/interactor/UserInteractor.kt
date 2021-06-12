package com.wainow.domain.interactor

import com.wainow.domain.entity.User

interface UserInteractor {
    suspend fun getUsers(): List<User>
    suspend fun saveUsers(users: List<User>)
    suspend fun getLocalUsers(): List<User>
    suspend fun deleteUsers()
}