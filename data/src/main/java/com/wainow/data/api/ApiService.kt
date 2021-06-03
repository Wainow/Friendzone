package com.wainow.data.api

import com.wainow.domain.entity.User
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object{
        const val DEFAULT_TOKEN = "e3672c23-b1a5-4ca7-bb77-b6580d75810c"
    }

    @GET("v0/b/candidates--questionnaire.appspot.com/o/users.json?")
    suspend fun getUsers(
        @Query("alt") alt: String = "media",
        @Query("token") token: String = DEFAULT_TOKEN
    ): List<User>
}