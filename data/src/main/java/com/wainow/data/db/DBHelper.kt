package com.wainow.data.db

import com.wainow.domain.entity.User
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DBHelper {
    companion object{
        fun getUsers(): List<User>{
            val realm = Realm.getDefaultInstance()
            val result: List<User> = Json{
                ignoreUnknownKeys = true
            }.decodeFromString(realm.where<UserObject>().findAll().asJSON())
            realm.close()
            return result
        }
        fun saveUsers(users: List<User>){
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
            realm.close()
        }
        fun deleteUsers(){
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()
            realm.close()
        }
    }
}