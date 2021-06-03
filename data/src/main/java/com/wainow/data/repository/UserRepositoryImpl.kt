package com.wainow.data.repository

import com.wainow.data.api.ApiHelper
import com.wainow.domain.repository.UserRepository

class UserRepositoryImpl(private val apiHelper: ApiHelper): UserRepository {
    override suspend fun getUsers() = apiHelper.getUsers()
}