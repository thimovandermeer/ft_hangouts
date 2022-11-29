package com.example.ft_hangouts.repositories.User

import android.util.Log
import com.example.ft_hangouts.User
import com.example.ft_hangouts.dao.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(
    private val userDao : UserDao) : UserRepository
{
    private val TAG = "UserRepository class"
    private var _localList: List<User> = emptyList()
    private var _uuid = 0

    init {
        Log.d(TAG, "INIT function")
        getAllOnStartUp()
    }

    override fun getUuid() : Int {
        return _uuid
    }

    override fun incrementUuid() {
        _uuid++
    }

    override fun getAllOnStartUp() {
        CoroutineScope(Dispatchers.IO).launch {
                _localList = retrieveAll()
        }
    }

    override fun getLocalList() : List<User> {
        return _localList
    }
    override suspend fun updateLocalList() {
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

    override fun searchLocalListUser(user : String) : User? {
        return _localList.find {it.Name == user}
    }

    override fun searchLocalListEmail(email: String) : User? {
        Log.d(TAG, email)
        Log.d(TAG, "Locallist = $_localList")
        return _localList.find {it.email == email}
    }

    override fun searchLocalListPassword(password: String) : User? {
        return _localList.find {it.password == password}
    }

    override fun retrieveAll() : List<User> {
        return this.userDao.queryAll()
    }

    override suspend fun save(username: String, email:String, password:String) {
        val uuid = getUuid()
        val new = User(uuid.toString(), username, password, email)
        incrementUuid()
        Log.d(TAG, "Save function is called")
        this.userDao.insert(new)
        updateLocalList()
    }

    override fun search(user: String) : User {
        Log.d(TAG, "Search function is called")
        return this.userDao.search(user)
    }
}
