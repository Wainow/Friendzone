package com.wainow.domain.interactor

import com.wainow.domain.entity.User
import com.wainow.domain.repository.UserRepository

class UserInteractorImpl(private val userRepository: UserRepository): UserInteractor {
    override suspend fun getUsers(): List<User> {
        return userRepository.getUsers()
    }
}