package com.example.bookhub.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bookhub.R
import com.example.bookhub.activity.DescriptionActivity
import com.example.bookhub.model.Book
import com.squareup.picasso.Picasso


// <DashboardRecyclerAdapter.DashboardViewHolder> -> this line will connect the adapter with view holder
class DashboardRecyclerAdapter(
    val context:Context,
    var itemList: ArrayList<Book>
):RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {

    // view holder class that contains the single row view of the list item
    class DashboardViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val textBookName:TextView = view.findViewById(R.id.txtBookName)
        val textBookAuthor:TextView = view.findViewById(R.id.txtBookAuthor)
        val textBookPrice:TextView = view.findViewById(R.id.txtBookPrice)
        val textBookRating:TextView = view.findViewById(R.id.txtBookRating)
        val imgBookImage:ImageView = view.findViewById(R.id.imgBookImage)
        val llContent:LinearLayout = view.findViewById(R.id.linearContent)
    }

    // it is responsible to create the first few views that are to be displayed at once on screens like as of (5 out of 100)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {

        // inflate the layout and then return the viewholder
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    // it takes the responsibility to recycle the views and bind the proper data
    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {

        // it will take data from the arrayList and update it on screen
        val book = itemList[position]
        holder.textBookName.text = book.bookName
        holder.textBookAuthor.text = book.bookAuthor
        holder.textBookPrice.text = book.bookPrice
        holder.textBookRating.text = book.bookRating
//      holder.imgBookImage.setImageResource(book.bookImage)
        Picasso.get().load(book.bookImage).error(R.drawable.default_book_cover).into(holder.imgBookImage)
        holder.llContent.setOnClickListener{
            val intent = Intent(context,DescriptionActivity::class.java)
            intent.putExtra("book_id",book.bookId)
            context.startActivity(intent)
        }
    }

    // returns the total number of items
    override fun getItemCount(): Int {

        return itemList.size
    }
}