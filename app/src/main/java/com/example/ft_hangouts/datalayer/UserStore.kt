package com.example.ft_hangouts.datalayer

import android.util.Log
import com.example.ft_hangouts.User

class UserStore(database: AppDatabase) {
    private val TAG = "User store class"
    val userDao = database.userDao()


    suspend fun save(user: User) {
        Log.d(TAG, "Save function is called")
        this.userDao.insert(user)
    }

}
