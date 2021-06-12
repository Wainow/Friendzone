package com.wainow.data.repository

import android.util.Log
import com.wainow.data.api.ApiHelper
import com.wainow.data.db.DBHelper
import com.wainow.data.db.FriendObject
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.wainow.data.db.UserObject
import com.wainow.domain.entity.User
import com.wainow.domain.repository.UserRepository
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import io.realm.kotlin.where

class UserRepositoryImpl(private val apiHelper: ApiHelper): UserRepository {
    override suspend fun getUsers() = apiHelper.getUsers()
    override suspend fun getLocalUsers() = DBHelper.getUsers()
    override suspend fun saveUsers(users: List<User>){ DBHelper.saveUsers(users) }
    override suspend fun deleteUsers() { DBHelper.deleteUsers() }
}