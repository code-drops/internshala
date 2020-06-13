package com.example.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_avengers.*
import java.nio.channels.InterruptedByTimeoutException

class AvengersActivity : AppCompatActivity() {

    // creating variable to send message to MessageActivity
    lateinit var etMessage:EditText
    lateinit var btnSendMessage:Button
    lateinit var btnLogout:Button
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avengers)

        // accessing the title from the sharedPreference
        sharedPreferences = getSharedPreferences(getString(R.string.preference_filename), Context.MODE_PRIVATE)
        var titlename = sharedPreferences.getString("title","The Avengers")

        title = titlename

        // connecting the views with variables
        etMessage = findViewById(R.id.editMessage)
        btnSendMessage = findViewById(R.id.btnSendMessage)
        btnLogout = findViewById(R.id.btnLogout)

        // setting the onclick event on button
        btnSendMessage.setOnClickListener {
            var intent = Intent(this@AvengersActivity,MessageActivity::class.java)
            if(etMessage.text.toString()==""){
                // if message is empty
                Toast.makeText(this@AvengersActivity,"No input message",Toast.LENGTH_SHORT).show()
            }else{
                // sending message to the display on MessageActivity
                intent.putExtra("Message",etMessage.text.toString())
                startActivity(intent)
            }
        }

        // logout click listener
        btnLogout.setOnClickListener {
            var intent = Intent(this@AvengersActivity,LoginActivity::class.java)
            sharedPreferences.edit().clear().apply()
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        etMessage.hint = "Enter message"
        // clearing message when back to this activity
        etMessage.text.clear()
    }
}
