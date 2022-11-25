package com.example.ft_hangouts.repositories

import android.util.Log
import com.example.ft_hangouts.User
import com.example.ft_hangouts.datalayer.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(database: AppDatabase) {
    private val TAG = "UserRepository class"
    val userDao = database.userDao()
    private var _localList: List<User> = emptyList()
    private var _uuid = 0

    fun getUuid() : Int {
        return _uuid
    }

    fun incrementUuid() {
        _uuid++
    }

    fun getAllOnStartUp() {
        CoroutineScope(Dispatchers.IO).launch {
                _localList = retrieveAll()
        }
    }

    fun getLocalList() : List<User> {
        return _localList
    }
    suspend fun updateLocalList() {
        val currentList = retrieveAll()
        Log.d(TAG, "currentlist = $currentList")
        if (_localList.isEmpty()) {
            _localList = currentList
        } else {
            if (!_localList.containsAll(currentList)) {
                _localList = currentList
            }
        }
        Log.d(TAG, "locallist in update list $_localList")
    }

    fun searchLocalListUser(user : String) : User? {
        return _localList.find {it.Name == user}
    }

    fun searchLocalListEmail(email: String) : User? {
        Log.d(TAG, email)
        Log.d(TAG, "Locallist = $_localList")
        return _localList.find {it.email == email}
    }

    fun searchLocalListPassword(password: String) : User? {
        return _localList.find {it.password == password}
    }

    fun retrieveAll() : List<User> {
        return this.userDao.queryAll()
    }

    suspend fun save(username: String, email:String, password:String) {
        val uuid = getUuid()
        val new = User(uuid.toString(), username, password, email)
        incrementUuid()
        Log.d(TAG, "Save function is called")
        this.userDao.insert(new)
        updateLocalList()
    }

    fun search(user: String) : User {
        Log.d(TAG, "Search function is called, ja toch")
        return this.userDao.search(user)
    }
}
