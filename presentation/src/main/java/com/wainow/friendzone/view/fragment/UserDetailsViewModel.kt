package com.wainow.friendzone.view.fragment

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.wainow.domain.entity.User
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserDetailsViewModel(state: SavedStateHandle) : ViewModel() {
    companion object {
        private const val USER_KEY = "userId"
    }
    private val savedStateHandle = state

    fun saveCurrentUser(user: User) {
        savedStateHandle.set(USER_KEY, Json.encodeToString(user))
    }
    fun getCurrentUser(): User? = Json.decodeFromString(savedStateHandle.get(USER_KEY) ?: "")
}