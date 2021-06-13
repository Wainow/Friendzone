package com.wainow.data.db

import com.wainow.data.utils.UserObjectMapper
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
                UserObjectMapper.map(user, realm)
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