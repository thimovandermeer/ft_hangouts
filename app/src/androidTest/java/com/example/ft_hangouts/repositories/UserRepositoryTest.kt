package com.example.ft_hangouts.repositories

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ft_hangouts.User
import com.example.ft_hangouts.dao.UserDao
import com.example.ft_hangouts.datalayer.AppDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {
    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase
    private lateinit var userRepository: UserRepository

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.databaseBuilder(context, AppDatabase::class.java, "test-database").build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun retrieveAll() {

    }

    @Test
    fun save() {
        val user = User("1", "Thimo", "Doeidoei","thimo@gmail.com")
        runBlocking {
            userRepository.save(user)
        }
        val byname = userRepository.search("Thimo")
        assertEquals(user, byname)

    }

    @Test
    fun search() {
    }

    @Test
    fun getUserDao() {
    }

    @Test
    fun updateLocalList() {
    }

    @Test
    fun searchLocalList() {
    }
}