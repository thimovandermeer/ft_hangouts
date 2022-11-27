package com.example.ft_hangouts.repositories.User

import com.example.ft_hangouts.User

interface UserRepository {

    fun getUuid() : Int
    fun incrementUuid()
    fun getAllOnStartUp()
    fun getLocalList() : List<User>
    suspend fun updateLocalList()
    fun searchLocalListUser(user : String) : User?
    fun searchLocalListEmail(email: String) : User?
    fun searchLocalListPassword(password: String) : User?
    fun retrieveAll() : List<User>
    suspend fun save(username: String, email:String, password:String)
    fun search(user: String) : User
}