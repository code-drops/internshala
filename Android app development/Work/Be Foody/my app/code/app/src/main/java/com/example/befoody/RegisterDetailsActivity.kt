package com.example.befoody

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register_details.*

class RegisterDetailsActivity : AppCompatActivity() {
    var name:String? = null
    var email:String? = null
    var mobile:String? = null
    var address:String? = null
    var password:String? = null

    lateinit var textInputName:TextView
    lateinit var textInputEmail:TextView
    lateinit var textInputMobile:TextView
    lateinit var textInputAddress:TextView
    lateinit var textInputPassword:TextView
    lateinit var btnBack:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_details)
        textInputName = findViewById(R.id.textInputName)
        textInputEmail =findViewById(R.id.textInputEmail)
        textInputMobile =findViewById(R.id.textInputMobile)
        textInputAddress =findViewById(R.id.textInputAddress)
        textInputPassword =findViewById(R.id.textInputPassword)
        btnBack = findViewById(R.id.btnBack)
        if(intent!=null){
            name = intent.getStringExtra("name")
            email = intent.getStringExtra("email")
            mobile = intent.getStringExtra("mobile")
            address = intent.getStringExtra("address")
            password = intent.getStringExtra("password")
        }

        textInputName.text = name
        textInputEmail.text = email
        textInputMobile.text = mobile
        textInputAddress.text = address
        textInputPassword.text = password

        Toast.makeText(this@RegisterDetailsActivity,"Registered Successfully",Toast.LENGTH_SHORT).show()

        btnBack.setOnClickListener{
            startActivity(Intent(this@RegisterDetailsActivity,RegisterActivity::class.java))
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}