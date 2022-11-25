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
        UserRepository.getAllOnStartUp()
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
        return UserRepository.searchLocalListUser(user) != null
    }

    private fun emailExists(email: String): Boolean {
        return UserRepository.searchLocalListEmail(email) != null
    }

    private fun passwordExists(password: String) : Boolean {
        return UserRepository.searchLocalListPassword(password) != null
    }

    private fun isNumeric(string : String) : Boolean {
        return string.all { char -> char.isDigit()}
    }

    fun userValid(username: String) : Boolean {
        if(isNumeric(username))
            return false
        return true
    }

    enum class state {USERNAMEINVALID,USERALREADYEXISTS, EMAILINVALID, EMAILEXISTS, PASSWORDINVALID, PASSWORDEXISTS, INPROGRESS, SUCCESS}

    private fun validateInput(username: String, email: String, password: String) : state {
        if (!userValid(username)) {
            return state.USERNAMEINVALID
        }

        if (userExists(username)) {
            return state.USERALREADYEXISTS
        }

        if (email.isEmpty() && isValidEmail(email)) {
            return state.EMAILINVALID
        }

        if (emailExists(email)) {
            Log.d(TAG, "Email exists")
            return state.EMAILEXISTS
        }

        if (!password.isEmpty() && !isValidPassword(password)) {
            return state.PASSWORDINVALID
        }

        if (passwordExists(password)) {
            return state.PASSWORDEXISTS
        }
        return state.INPROGRESS
    }

    suspend fun handleInput(
        username : String,
        email : String,
        password: String
    ) : state {
        var ret : state;
        Log.d(TAG, "Handle input called")
        ret = validateInput(username, email, password)
        Log.d(TAG, "State is after validation $ret")
        if (ret == state.INPROGRESS) {
            Log.d(TAG, "State success")
            insertUser(username, email, password)
            ret = state.SUCCESS
        }
        return ret
    }

    suspend fun insertUser(username: String, email: String, password: String) {
        Log.d(TAG, "Insert in database")
        coroutineScope {
            launch {
                UserRepository.save(username, email, password)
            }
        }
    }

    private suspend fun searchUser(user : String) = coroutineScope {
        Log.d(TAG, "Searching user in database")
        UserRepository.search(user)
    }


}