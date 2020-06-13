package com.example.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MessageActivity : AppCompatActivity() {

    lateinit var textMessage:TextView
    lateinit var btnBack:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        // connecting the textView to the variable
        textMessage = findViewById(R.id.textMessage)
        btnBack = findViewById(R.id.btnBack)

        if(intent!=null){
            var message = intent.getStringExtra("Message")
            textMessage.text = message
        }

        btnBack.setOnClickListener{
            var intent = Intent(this@MessageActivity,AvengersActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}