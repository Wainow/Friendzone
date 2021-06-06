package com.wainow.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Friend (val id: Long)

@Serializable
data class User (
        val id: Long,
        val guid: String,
        val isActive: Boolean,
        val balance: String,
        val age: Long,
        val eyeColor: String,
        val name: String,
        val gender: String,
        val company: String,
        val email: String,
        val phone: String,
        val address: String,
        val about: String,
        val registered: String,
        val latitude: Double,
        val longitude: Double,
        val tags: List<String>,
        val friends: List<Friend>,
        val favoriteFruit: String
)