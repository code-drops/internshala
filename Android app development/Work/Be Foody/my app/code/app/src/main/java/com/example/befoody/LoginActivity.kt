package com.example.befoody

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    var validNUmber ="0123456789"
    var validPassword = "qwerty"
    lateinit var editMobile:EditText
    lateinit var editPassword:EditText
    lateinit var btnLogin:Button
    lateinit var textForgotPassword:TextView
    lateinit var textRegister:TextView

    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences(getString(R.string.food_preferences), Context.MODE_PRIVATE)
        var isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false)
        if(isLoggedIn){
            var intent = Intent(this@LoginActivity,LoginDetailsActivity::class.java)
            startActivity(intent)
            finish()
        }
        editMobile = findViewById(R.id.editMobile)
        editPassword = findViewById(R.id.editPassword)
        btnLogin = findViewById(R.id.btnLogin)
        textForgotPassword = findViewById(R.id.textForgotPassword)
        textRegister = findViewById(R.id.textRegister)

        btnLogin.setOnClickListener{
            val number = editMobile.text.toString()
            val password = editPassword.text.toString()
            if(number==validNUmber && password==validPassword){
                var intent = Intent(this@LoginActivity,LoginDetailsActivity::class.java)
                var isLoggedIn = sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
                sharedPreferences.edit().putString("number",number).apply()
                sharedPreferences.edit().putString("password",password).apply()
                startActivity(intent)
            }
            else if(number=="" || password==""){
                Toast.makeText(this@LoginActivity,"Empty Credentials",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this@LoginActivity,"Wrong Credentials",Toast.LENGTH_SHORT).show()
            }
        }
        textRegister.setOnClickListener{
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
        }
        textForgotPassword.setOnClickListener{
            startActivity(Intent(this@LoginActivity,ForgotPasswordActivity::class.java))
        }
    }

    override fun onPause() {
        super.onPause()
        var isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false)
        if(isLoggedIn){
            finish()
        }
    }
}