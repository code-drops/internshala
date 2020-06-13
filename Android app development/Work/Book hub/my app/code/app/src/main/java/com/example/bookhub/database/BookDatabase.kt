package com.example.bookhub.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookhub.model.Book

// it is used to tell the compiler that the bookDatabase class will behave like database
@Database(entities = [BookEntity::class],version = 1)
abstract class BookDatabase :RoomDatabase(){
    abstract fun bookDao():BookDao
}