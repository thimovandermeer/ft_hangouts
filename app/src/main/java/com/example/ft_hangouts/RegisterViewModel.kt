package com.example.ft_hangouts

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import com.example.ft_hangouts.datalayer.AppDatabase
import com.example.ft_hangouts.repositories.UserRepository
import kotlinx.coroutines.*


@Entity
data class User(


    @PrimaryKey val uid: String,
    @ColumnInfo(name = "Name") val Name: String?,
    @ColumnInfo(name = "Password") val password: String?,
    @ColumnInfo(name = "Email") val email: String?
)

class RegisterViewModel(application: Application): AndroidViewModel(application) {

    private val TAG = "RegisterViewModel"

    val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-name"
    ).build()

    private var UserRepository: UserRepository
    init {
        UserRepository = UserRepository(database = db)
    }

    private fun isEmpty(str: CharSequence?): Boolean {
        return str == null || str.length == 0
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !isEmpty(target)
    }

    fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.filter { it.isDigit() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) return false
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

        return true
    }

    private fun userExists(user : String) :Boolean {
        return UserRepository.searchLocalList(user) != null
    }

    fun userInvalid() : Boolean {
        // digt moe
        return true
    }

    fun handleInput(
        username : String,
        email : String,
        password: String
    ) {
        if (isValidEmail(email)) {
            // toast message that email is invalid
        }

        if (!isValidPassword(password)) {
            // toast message that password is invalid
        }

        if (userInvalid()) {
            // toast message user invalid
        }

        if (userExists(username)) {
            // toast message user already exists
        }


        val new = User("0",username, password, email)
        Log.d(TAG, "Inside suspend function")
        insertUser(new)
        Log.d(TAG, username)
        Log.d(TAG, email)
        Log.d(TAG, password)
    }

    fun insertUser(user: User) = runBlocking{
        Log.d(TAG, "Insert in database")
        UserRepository.save(user)
    }

    private suspend fun searchUser(user : String) = coroutineScope {

        Log.d(TAG, "Searching user in database")
        UserRepository.search(user)
    }


}