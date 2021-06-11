package com.wainow.data.repository

import android.util.Log
import com.wainow.data.api.ApiHelper
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

    override fun getLocalUsers(): List<User> {
        var data: String = ""
        val realm = Realm.getDefaultInstance()
        realm.executeTransactionAsync{ realm ->
            data = realm.where<UserObject>().findAll().asJSON()
        }
        return Json{
            ignoreUnknownKeys = true
        }.decodeFromString(realm.where<UserObject>().findAll().asJSON())
    }

    override fun saveUsers(users: List<User>){
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction{ realm.deleteAll() }
        for(user in users) {
            realm.beginTransaction()
            val realmUser = realm.createObject<UserObject>(user.id)
            realmUser.apply {
                name = user.name
                guid = user.guid
                isActive = user.isActive
                balance = user.balance
                age = user.age
                eyeColor = user.eyeColor
                gender = user.gender
                company = user.company
                email = user.email
                phone = user.phone
                address = user.address
                about = user.about
                registered = user.registered
                latitude = user.latitude
                longitude = user.longitude
                favoriteFruit = user.favoriteFruit
                for(tag in user.tags){
                    tags.add(tag)
                }
                for(friend in user.friends){
                    val realmFriend = FriendObject(friend.id)
                    friends.add(realm.copyToRealm(realmFriend))
                }
            }
            realm.commitTransaction()
        }
    }

    override fun deleteUsers() {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
    }
}