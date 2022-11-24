package com.example.ft_hangouts.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ft_hangouts.User

@Dao
interface UserDao {
    @Query("Select * from user order by uid")
    fun queryAll() : List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
//
//    @Query("select count(*) from users")
//    suspend fun count() : Int

    @Query("SELECT * FROM user WHERE name LIKE :search")
    fun search(search: String?): User
}