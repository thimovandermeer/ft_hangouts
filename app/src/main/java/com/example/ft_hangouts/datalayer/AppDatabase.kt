package com.example.ft_hangouts.datalayer

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ft_hangouts.User
import com.example.ft_hangouts.dao.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    private val TAG = "MyActivity"
    abstract fun userDao() : UserDao
}