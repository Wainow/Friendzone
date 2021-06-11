package com.wainow.data.db

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserObject(
    @PrimaryKey
    var id: Long = 0,
    var guid: String = "",
    var isActive: Boolean = false,
    var balance: String = "",
    var age: Long = 0,
    var eyeColor: String = "",
    var name: String = "",
    var gender: String = "",
    var company: String = "",
    var email: String = "",
    var phone: String = "",
    var address: String = "",
    var about: String = "",
    var registered: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var tags: RealmList<String> = RealmList(),
    var friends: RealmList<FriendObject> = RealmList(),
    var favoriteFruit: String = ""
): RealmObject()

open class FriendObject(
    private var id: Long = 0
): RealmObject()