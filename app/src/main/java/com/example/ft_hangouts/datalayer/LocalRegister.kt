package com.example.ft_hangouts.datalayer

import androidx.room.Entity
import androidx.room.PrimaryKey

class LocalRegister {
    @Entity(tableName = "Users")
    data class LocalUser(
        @PrimaryKey val value:String
    )
}