package com.example.befoody

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ForgotPasswordActivity : AppCompatActivity() {

    lateinit var editMobile:EditText
    lateinit var editEmail:EditText
    lateinit var btnNext:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        editMobile = findViewById(R.id.editMobile)
        editEmail = findViewById(R.id.editEmail)
        btnNext = findViewById(R.id.btnNext)

        btnNext.setOnClickListener{
            if(editMobile.text.toString()=="" || editEmail.text.toString()==""){
                Toast.makeText(this@ForgotPasswordActivity, "Empty Credentials", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this@ForgotPasswordActivity,ForgotPasswordDetailsActivity::class.java)
                intent.putExtra("email",editEmail.text.toString())
                intent.putExtra("mobile",editMobile.text.toString())
                startActivity(intent)
            }
        }
    }
    override fun onPause() {
        super.onPause()
        finish()
    }
}