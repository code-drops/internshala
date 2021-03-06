package com.example.bookhub.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookhub.R
import com.example.bookhub.database.BookDatabase
import com.example.bookhub.database.BookEntity
import com.example.bookhub.util.ConnectionManager
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text

class DescriptionActivity : AppCompatActivity() {

    lateinit var txtBookName: TextView
    lateinit var txtBookAuthor: TextView
    lateinit var txtBookPrice: TextView
    lateinit var txtBookRating: TextView
    lateinit var imgBookImage: ImageView
    lateinit var txtBookDesc: TextView
    lateinit var btnAddToFav: Button
    lateinit var progressBar: ProgressBar
    lateinit var progressLayout: RelativeLayout
    var bookId: String? = "100"
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        txtBookName = findViewById(R.id.txtBookName)
        txtBookAuthor = findViewById(R.id.txtBookAuthor)
        txtBookPrice = findViewById(R.id.txtBookPrice)
        txtBookRating = findViewById(R.id.txtBookRating)
        imgBookImage = findViewById(R.id.imgBookImage)
        txtBookDesc = findViewById(R.id.txtBookDesc)
        btnAddToFav = findViewById(R.id.btnAddToFav)
        progressBar = findViewById(R.id.progressBar)
        progressLayout = findViewById(R.id.progressLayout)
        toolbar = findViewById(R.id.toolbar)


        setSupportActionBar(toolbar)
        supportActionBar?.title = "Book Details"

//        to visible the progress bar on opening the screen
        progressLayout.visibility = View.VISIBLE
        progressBar.visibility = View.VISIBLE

//        obtain the book id from intent
        if (intent != null) {
            bookId = intent.getStringExtra("book_id")
        } else {
            finish()
            Toast.makeText(this@DescriptionActivity, "Error occurred", Toast.LENGTH_SHORT).show()
        }

        if (bookId == "100") {
            finish()
            Toast.makeText(this@DescriptionActivity, "Error occurred", Toast.LENGTH_SHORT).show()
        }

//        queue for the requests
        val queue = Volley.newRequestQueue(this@DescriptionActivity)
        val url = "http://13.235.250.119/v1/book/get_book/"

//        json object to send to server in POST request
        val jsonParams = JSONObject()
        jsonParams.put("book_id", bookId)

        if(ConnectionManager().checkConnectivity(this@DescriptionActivity)){
            //        json request
            val jsonRequest =
                object : JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener {

                    try{
                        val success = it.getBoolean("success")
                        if(success){
                            val bookJsonObject = it.getJSONObject("book_data")
                            progressLayout.visibility = View.GONE

                            val bookImageurl = bookJsonObject.getString("image")
                            Picasso.get().load(bookJsonObject.getString("image")).error(R.drawable.default_book_cover).into(imgBookImage)
                            txtBookName.text = bookJsonObject.getString("name")
                            txtBookAuthor.text = bookJsonObject.getString("author")
                            txtBookPrice.text = bookJsonObject.getString("price")
                            txtBookRating.text = bookJsonObject.getString("rating")
                            txtBookDesc.text = bookJsonObject.getString("description")

                            val bookEntity = BookEntity(
                                bookId?.toInt() as Int,
                                txtBookName.text.toString(),
                                txtBookAuthor.text.toString(),
                                txtBookPrice.text.toString(),
                                txtBookRating.text.toString(),
                                txtBookDesc.text.toString(),
                                bookImageurl
                            )
//                            Check whether the book belongs to the favourites or not
                            val checkFav = DBAsyncTask(applicationContext,bookEntity,1).execute()
                            val isFav = checkFav.get()
                            if(isFav){
//                                if book is in favourites
                                btnAddToFav.text = "Remove from Favourites"
                                val favColor = ContextCompat.getColor(applicationContext,R.color.colorFavourite)
                                btnAddToFav.setBackgroundColor(favColor)
                            }else{
//                                if book is not in favourites
                                btnAddToFav.text = "Add to Favourites"
                                val noFavColor = ContextCompat.getColor(applicationContext,R.color.colorPrimary)
                                btnAddToFav.setBackgroundColor(noFavColor)
                            }

//                            when the button got clicked
                            btnAddToFav.setOnClickListener{
//                                when book is not in favourites
                                if(!DBAsyncTask(applicationContext,bookEntity,1).execute().get()){
                                    val async = DBAsyncTask(applicationContext,bookEntity,2).execute()
                                    val result = async.get()
                                    if(result){
                                        Toast.makeText(this@DescriptionActivity,"Book added to favourites",Toast.LENGTH_SHORT).show()
                                        btnAddToFav.text = "Remove from Favourites"
                                        val favColor = ContextCompat.getColor(applicationContext,R.color.colorFavourite)
                                        btnAddToFav.setBackgroundColor(favColor)
                                    }else{
                                        Toast.makeText(this@DescriptionActivity,"Some error occurred",Toast.LENGTH_SHORT).show()
                                    }
                                }else{
                                    val async = DBAsyncTask(applicationContext,bookEntity,3).execute()
                                    val result = async.get()
                                    if(result){
                                        Toast.makeText(this@DescriptionActivity,"Book removed from favourites",Toast.LENGTH_SHORT).show()
                                        btnAddToFav.text = "Add to Favourites"
                                        val noFavColor = ContextCompat.getColor(applicationContext,R.color.colorPrimary)
                                        btnAddToFav.setBackgroundColor(noFavColor)
                                    }else{
                                        Toast.makeText(this@DescriptionActivity,"Some error occurred",Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }else{
                            Toast.makeText(this@DescriptionActivity,"Some error occured",Toast.LENGTH_SHORT).show()
                        }
                    }catch (e: JSONException){
                        Toast.makeText(this@DescriptionActivity,"Some error occured",Toast.LENGTH_SHORT).show()
                    }
                }, Response.ErrorListener {
                    Toast.makeText(this@DescriptionActivity,"Volley error occured",Toast.LENGTH_SHORT).show()
                }) {
                    override fun getHeaders(): MutableMap<String, String> {
                        val headers = HashMap<String, String>()
                        headers["Content-type"] = "application/json"
                        headers["token"] = "9b51d51d127a70"
                        return headers
                    }
                }
            queue.add(jsonRequest)
        }
        else{
            val dialog = AlertDialog.Builder(this@DescriptionActivity)
            dialog.setTitle("Error")
            dialog.setMessage("Internet connection not found")
            dialog.setPositiveButton("Open Settings") { text, listener ->
                val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                finish()

            }
            dialog.setNegativeButton("Exit") { text, listener ->
//                it will close the app from any activity
                ActivityCompat.finishAffinity(this@DescriptionActivity)
            }
            dialog.create()
            dialog.show()
        }
    }



//    as a class cannot inherit two classes , therefore AsyncTask Class has to be defined inside it
    class DBAsyncTask(val context: Context,val bookEntity: BookEntity,val mode:Int) : AsyncTask<Void,Void,Boolean>() {

    /*
    Mode 1 -> Check that if the book is in favourites or not
    Mode 2 -> save the book in favourites
    mode 3 -> remove the favourites book
    * */
        //    database object
    val db = Room.databaseBuilder(context,BookDatabase::class.java,"books-db").build()

    override fun doInBackground(vararg params: Void?): Boolean {
        when(mode){
            1 -> {
//                Check that if the book is in favourites or not
                val book:BookEntity? = db.bookDao().getBookById(bookEntity.book_id.toString())
                db.close()
                return book!= null                  // it will return false if the value is null
            }
            2 -> {
//                save the book in favourites
                db.bookDao().insertBook(bookEntity)
                db.close()
                return true
            }
            3 -> {
//                remove the favourites book
                db.bookDao().deleteBook(bookEntity)
                db.close()
                return true
            }
        }
            return false
        }
    }
}