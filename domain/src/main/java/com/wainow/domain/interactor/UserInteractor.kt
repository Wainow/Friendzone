package com.wainow.domain.interactor

import com.wainow.domain.entity.User

interface UserInteractor {
    suspend fun getUsers(): List<User>
}