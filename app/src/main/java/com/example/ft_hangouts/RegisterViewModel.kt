package com.example.ft_hangouts

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Room
import com.example.ft_hangouts.datalayer.AppDatabase
import com.example.ft_hangouts.datalayer.UserStore
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.runBlocking

@Entity
data class User(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "Name") val Name: String?,
    @ColumnInfo(name = "Password") val lastName: String?,
    @ColumnInfo(name = "Email") val email: String?
)

class RegisterViewModel(application: Application): AndroidViewModel(application) {

    private val TAG = "RegisterViewModel"

    val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-name"
    ).build()

    private var UserStore: UserStore
    init {
        UserStore = UserStore(database = db)
    }

    fun handleInput(
        username : String,
        email : String,
        password: String
    ) {
        val new = User("0",username, password, email)
        Log.d(TAG, "Kom ik hier?")
        Log.d(TAG, "Inside suspend function")
        insertUser(new)
        Log.d(TAG, username)
        Log.d(TAG, email)
        Log.d(TAG, password)
    }

    private fun insertUser(user: User) = runBlocking{
        Log.d(TAG, "Insert in database")
        UserStore.save(user)
    }


}