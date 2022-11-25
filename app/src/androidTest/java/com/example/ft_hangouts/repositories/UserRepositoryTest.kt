package com.example.ft_hangouts.repositories

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.ft_hangouts.User
import com.example.ft_hangouts.dao.UserDao
import com.example.ft_hangouts.datalayer.AppDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {
    private val TAG = "UserRepositorytest class"

    private var userDao: UserDao
    private var db: AppDatabase
    init {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.databaseBuilder(context, AppDatabase::class.java, "test-database").build()
        userDao = db.userDao()
    }
    private var userRepository : UserRepository
    init {
        userRepository = UserRepository(database = db)
    }


    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun Testsave() {
        val user = User(userRepository.getUuid().toString(), "Thimo", "Doeidoei","thimo@gmail.com")
        runBlocking {
            userRepository.save("Thimo","thimo@gmail.com", "Doeidoei")
        }
        val byname = userRepository.search("Thimo")
        assertEquals(user, byname)
    }

    fun create_database_members() {
        var i = 0
        while(i != 100) {
            runBlocking {
                userRepository.save("thimo$i", "Doeidoei$i", "Thimo$i@gmail.com")
            }
            i++
        }
    }

    @Test
    fun Testsearch() {
        create_database_members();
        var match = userRepository.search("thimo99")
        assertEquals("thimo99", match.Name)
    }

    @Test
    fun TestupdateLocalList() {
        val oldsize = userRepository.getLocalList().size
        Log.d(TAG, "Size is $oldsize")
        val user = User("101", "Karina", "hallohallo", "karina@gmail.com")
        runBlocking {
            userRepository.save( "Karina", "hallohallo", "karina@gmail.com")
        }
        val newsize = userRepository.getLocalList().size
        Log.d(TAG, "New size = $newsize")
        assertEquals(true, userRepository.getLocalList().contains(user))
    }

    @Test
    fun TestsearchLocalListUser() {
        val user = userRepository.searchLocalListUser("thimo80")
        if (user != null) {
            assertEquals("thimo80", user.Name)
        }
    }

    @Test
    fun TestsearchLocalListEmail() {
        val user = userRepository.searchLocalListEmail("thimo@gmail.com")
        if (user != null) {
            assertEquals("thimo@gmail.com", user.email)
        }
    }

    @Test
    fun TestsearchLocalListPassword() {
        val user = userRepository.searchLocalListPassword("doeidoei")
        if (user != null) {
            assertEquals("doeidoei", user.password)
        }
    }

    @Test
    fun TestRetrieveall() {
        runBlocking {
            val list = userRepository.retrieveAll()
            assertEquals(false, list.isEmpty())
        }
    }
}