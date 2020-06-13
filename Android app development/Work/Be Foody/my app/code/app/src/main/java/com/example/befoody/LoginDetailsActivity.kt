package com.example.befoody

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class LoginDetailsActivity : AppCompatActivity() {

    lateinit var btnLogout: Button
    lateinit var textInputNumber:TextView
    lateinit var textInputPassword:TextView
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_details)

        textInputNumber = findViewById(R.id.textInputNumber)
        textInputPassword = findViewById(R.id.textInputPassword)
        btnLogout = findViewById(R.id.btnLogout)
        sharedPreferences =
            getSharedPreferences(getString(R.string.food_preferences), Context.MODE_PRIVATE)

        var number = sharedPreferences.getString("number","null")
        var password = sharedPreferences.getString("password","null")

        textInputNumber.text = number
        textInputPassword.text = password

        btnLogout.setOnClickListener {
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
            var intent = Intent(this@LoginDetailsActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        sharedPreferences =
            getSharedPreferences(getString(R.string.food_preferences), Context.MODE_PRIVATE)
        var isLoggedIn = sharedPreferences.getBoolean("IsLoggedIn", false)
        if (!isLoggedIn) {
            finish()
        }
    }
}