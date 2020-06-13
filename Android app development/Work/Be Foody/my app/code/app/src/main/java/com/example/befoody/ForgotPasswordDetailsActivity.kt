package com.example.befoody

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ForgotPasswordDetailsActivity : AppCompatActivity() {

    var email:String? = null
    var mobile:String? = null
    lateinit var textInputEmail: TextView
    lateinit var textInputMobile: TextView
    lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password_details)

        textInputEmail =findViewById(R.id.textInputEmail)
        textInputMobile =findViewById(R.id.textInputMobile)
        btnBack = findViewById(R.id.btnBack)

        if(intent!=null){
            email = intent.getStringExtra("email")
            mobile = intent.getStringExtra("mobile")
        }

        textInputEmail.text = email
        textInputMobile.text = mobile

        Toast.makeText(this@ForgotPasswordDetailsActivity,"Done", Toast.LENGTH_SHORT).show()

        btnBack.setOnClickListener{
            startActivity(Intent(this@ForgotPasswordDetailsActivity,ForgotPasswordActivity::class.java))
        }
    }
}