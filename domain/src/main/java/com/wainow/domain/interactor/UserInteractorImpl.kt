package com.wainow.domain.interactor

import com.wainow.domain.entity.User
import com.wainow.domain.repository.UserRepository

class UserInteractorImpl(private val userRepository: UserRepository): UserInteractor {
    override suspend fun getUsers() = userRepository.getUsers()
    override suspend fun saveUsers(users: List<User>) { userRepository.saveUsers(users) }
    override suspend fun getLocalUsers(): List<User> = userRepository.getLocalUsers()
    override suspend fun deleteUsers() { userRepository.deleteUsers() }
}