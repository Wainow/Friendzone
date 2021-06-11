package com.wainow.domain.repository

import com.wainow.domain.entity.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    fun getLocalUsers(): List<User>
    fun saveUsers(users: List<User>)
    fun deleteUsers()
}