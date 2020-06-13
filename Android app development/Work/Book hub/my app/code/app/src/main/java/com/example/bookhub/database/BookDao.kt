package com.example.bookhub.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

// It will contain the insert,delete functions
@Dao
interface BookDao {

//    It is taken care by the room library
    @Insert
    fun insertBook(bookEntity: BookEntity)
//    It is taken care by the room library
    @Delete
    fun deleteBook(bookEntity: BookEntity)

//    We have to write a query for it
//    It is used to display the books in favourites fragment not in the dashboard fragment
    @Query("SELECT * FROM books")
    fun getAllBooks():List<BookEntity>

//     to check whether the a specific book is in favourites or not
//    :bookId represents that the value will come from the function
    @Query("SELECT * FROM books WHERE book_id = :bookId")
    fun getBookById(bookId:String):BookEntity
}