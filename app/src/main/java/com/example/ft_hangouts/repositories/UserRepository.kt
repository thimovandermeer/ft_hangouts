package com.example.ft_hangouts.repositories

import android.util.Log
import com.example.ft_hangouts.User
import com.example.ft_hangouts.datalayer.AppDatabase

class UserRepository(database: AppDatabase) {
    private val TAG = "User store class"
    val userDao = database.userDao()
    private var localList: List<User> = emptyList()


    suspend fun updateLocalList() {
        val currentList = retrieveAll()
        if (localList.isEmpty()) {
            localList = currentList
        } else {
            if (!localList.containsAll(currentList)) {
                localList = currentList
            }
        }
    }

    fun searchLocalList(user : String) : User? {
        return localList.find {it.Name == user}
    }

    suspend fun retrieveAll() : List<User> {
        return this.userDao.queryAll()
    }

    suspend fun save(user: User) {
        Log.d(TAG, "Save function is called")
        this.userDao.insert(user)
        updateLocalList()
    }

    fun search(user: String) : User {
        Log.d(TAG, "Search function is called")
        return this.userDao.search(user)
    }

}
