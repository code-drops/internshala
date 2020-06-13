package com.example.activitylifecycle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var etMobileNumber:EditText
    lateinit var etPassword:EditText
    lateinit var btnLogin:Button
    lateinit var textForgotPassword:TextView
    lateinit var textRegister:TextView

    // variable to deal with shared preferences
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = "Log In"
        // shared preferences
        // R.string.preference_filename will be the id of the filename  and getString will access the string stored on that id
        sharedPreferences = getSharedPreferences(getString(R.string.preference_filename), Context.MODE_PRIVATE)

        // if the user is logged in then variable will store the default value i.e. false ... otherwise it will store true in it if user is logged in
        var isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false)
        if(isLoggedIn){
            val intent = Intent(this@LoginActivity,AvengersActivity::class.java)
            startActivity(intent)
            finish()
        }


        // creating variables to connect with views
        etMobileNumber = findViewById(R.id.editMobileNumber)
        etPassword = findViewById(R.id.editPassword)
        btnLogin = findViewById(R.id.btnLogin)
        textForgotPassword = findViewById(R.id.textForgotPassword)
        textRegister = findViewById(R.id.textRegister)

        val validMobileNumber = "0123456789"
        val validPassword = arrayOf("tony","steve","bruce","thanos")

        // method to handle onClick listener
        btnLogin.setOnClickListener {
            val mobileNumber = etMobileNumber.text.toString()
            val password = etPassword.text.toString()
            var nameOfAvenger: String = ""

            val intent = Intent(this@LoginActivity,AvengersActivity::class.java)
            if(validMobileNumber==mobileNumber){
                when (password) {
                    validPassword[0] -> {
                        nameOfAvenger = "Iron Man"
                        savePreferences(nameOfAvenger)
                        startActivity(intent)
                    }
                    validPassword[1] -> {
                        nameOfAvenger = "Captain America"
                        savePreferences(nameOfAvenger)
                        startActivity(intent)
                    }
                    validPassword[2] -> {
                        nameOfAvenger = "Hulk"
                        savePreferences(nameOfAvenger)
                        startActivity(intent)
                    }
                    "" -> {
                        Toast.makeText(
                            this@LoginActivity,
                            "Enter Password",
                            Toast.LENGTH_LONG).show()
                    }
                    in validPassword -> {
                        nameOfAvenger = "Avengers"
                        savePreferences(nameOfAvenger)
                        startActivity(intent)
                    }
                    else -> {
                        Toast.makeText(
                            this@LoginActivity,
                            "Wrong credentials",
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
            // for wrong credentials
            else{
                Toast.makeText(
                    this@LoginActivity,
                    "Wrong credentials",
                    Toast.LENGTH_LONG).show()
            }
        }

        // for forgot password activity
        textForgotPassword.setOnClickListener{
            val intent =Intent(this@LoginActivity,ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // for register activity
        textRegister.setOnClickListener{
            val intent =Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun savePreferences(title: String){
        // it will write the isLoggedIn key with its boolean value in file iff user in logged in
        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()

        // to access the title of when the user is already logged In
        sharedPreferences.edit().putString("title",title).apply()
    }

    override fun onPause() {
        super.onPause()
        sharedPreferences = getSharedPreferences(getString(R.string.preference_filename), Context.MODE_PRIVATE)
        var isLoggedIn = sharedPreferences.getBoolean("isLoggedIn",false)
        if(isLoggedIn){
            finish()
        }
    }


}
