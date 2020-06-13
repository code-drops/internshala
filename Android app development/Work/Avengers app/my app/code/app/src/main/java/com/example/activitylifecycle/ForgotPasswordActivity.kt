package com.example.activitylifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var btnBack:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener{
            var intent = Intent(this@ForgotPasswordActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
