package com.example.ft_hangouts.datalayer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.ft_hangouts.User

@Dao
interface UserDao {
//    @Query("Select * from users order by value")
//    fun queryAll() : List<LocalRegister>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
//
//    @Query("select count(*) from users")
//    suspend fun count() : Int
}