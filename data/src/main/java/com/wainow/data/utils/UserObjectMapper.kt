package com.wainow.data.utils

import com.wainow.data.db.FriendObject
import com.wainow.data.db.UserObject
import com.wainow.domain.entity.User
import io.realm.Realm
import io.realm.RealmObject
import io.realm.kotlin.createObject

class UserObjectMapper {
    companion object{
        fun map(user: User, realm: Realm): RealmObject =
                realm.createObject<UserObject>(user.id).apply {
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
    }
}