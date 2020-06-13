package com.example.bookhub.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.security.ConfirmationNotAvailableException
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.bookhub.R
import com.example.bookhub.adapter.DashboardRecyclerAdapter
import com.example.bookhub.model.Book
import com.example.bookhub.util.ConnectionManager
import org.json.JSONException
import java.util.*
import kotlin.Comparator
import kotlin.collections.HashMap

class DashboardFragment : Fragment() {

    lateinit var recyclerDashboard:RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter:DashboardRecyclerAdapter
    lateinit var progressLayout:RelativeLayout
    lateinit var progressBar:ProgressBar
    var bookInfoList = arrayListOf<Book>()

//    compare ratings of two books passed as parameter
    var ratingComparator =Comparator<Book>{ book1,book2 ->
        if(book1.bookRating.compareTo(book2.bookRating,ignoreCase = true)==0){
//            if the  ratings are same, then sort according to names
            book1.bookName.compareTo(book2.bookName,ignoreCase = true)
        }else{
            book1.bookRating.compareTo(book2.bookRating,ignoreCase = true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        recyclerDashboard = view.findViewById(R.id.recyclerDashboard)
        progressLayout = view.findViewById(R.id.progressLayout)
        progressBar = view.findViewById(R.id.progressBar)
        layoutManager = LinearLayoutManager(activity)

//        It tells the compiler that the toolbar has options(defined in the last of this file)
//        It is only used for fragment ..
        setHasOptionsMenu(true)

//        to view the progress bar initially
        progressLayout.visibility = View.VISIBLE


//      to make queue of requests
        val queue = Volley.newRequestQueue(activity as Context)

//      url of the link from where the data is to be recieved
        val url = "http://13.235.250.119/v1/book/fetch_books/"

        if(ConnectionManager().checkConnectivity(activity as Context)){
//            If internet is available
//          JSON object requests
            val jsonObjectRequests = object :JsonObjectRequest(Request.Method.GET,url,null,
                Response.Listener{
                    try{
                        progressLayout.visibility = View.GONE
                        var success = it.getBoolean("success")
                        if (success){
                            val data = it.getJSONArray("data")
                            for (i in 0 until data.length()){
                                val bookJsonObject = data.getJSONObject(i)
                                val bookObject= Book(bookJsonObject.getString("book_id"),
                                    bookJsonObject.getString("name"),
                                    bookJsonObject.getString("author"),
                                    bookJsonObject.getString("rating"),
                                    bookJsonObject.getString("price"),
                                    bookJsonObject.getString("image")
                                )
                                bookInfoList.add(bookObject)
                                // "as" represents type casting
                                recyclerAdapter = DashboardRecyclerAdapter(activity as Context,bookInfoList)

                                // connecting the adapter and layout manager with xml file
                                recyclerDashboard.adapter = recyclerAdapter
                                recyclerDashboard.layoutManager = layoutManager
                            }
                        }else{
                            Toast.makeText(activity as Context,"Some error occured",Toast.LENGTH_SHORT).show()
                        }
                    }catch (e:JSONException){
                        Toast.makeText(activity as Context,"Some error occured",Toast.LENGTH_SHORT).show()
                    }
                },
                Response.ErrorListener{
                    if(activity!=null){
                        Toast.makeText(activity as Context,"Volley Error occurred!!!",Toast.LENGTH_SHORT).show()
                    }
                }){
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String,String>()
                    headers["Content-type"] = "application/json"
                    headers["token"] = "9b51d51d127a70"
                    return headers
                }
            }
            queue.add(jsonObjectRequests)
        }else {
            val dialog = AlertDialog.Builder(activity as Context)
            dialog.setTitle("Error")
            dialog.setMessage("Internet connection not found")
            dialog.setPositiveButton("Open Settings") { text, listener ->
                val settingsIntent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
                startActivity(settingsIntent)
                activity?.finish()

            }
            dialog.setNegativeButton("Exit") { text, listener ->
//                it will close the app from any activity
                ActivityCompat.finishAffinity(activity as Activity)
            }
            dialog.create()
            dialog.show()
        }
        return view
    }

//    to sort the bookInfoList
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item?.itemId
        if(id == R.id.action_sort){
            Collections.sort(bookInfoList,ratingComparator)
            bookInfoList.reverse()
        }
//    to tell the adapter that the order is changed
        recyclerAdapter.notifyDataSetChanged()
        return super.onOptionsItemSelected(item)
    }

//    it is  used to add menu to toolbar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_dashboard,menu)
    }
}