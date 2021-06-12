package com.wainow.domain.repository

import com.wainow.domain.entity.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getLocalUsers(): List<User>
    suspend fun saveUsers(users: List<User>)
    suspend fun deleteUsers()
}