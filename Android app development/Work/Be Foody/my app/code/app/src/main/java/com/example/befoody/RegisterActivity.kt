package com.example.befoody

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    lateinit var editName:EditText
    lateinit var editEmail:EditText
    lateinit var editMobile:EditText
    lateinit var editAddress:EditText
    lateinit var editPassword:EditText
    lateinit var editConfirmPassword:EditText
    lateinit var btnRegister:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editName = findViewById(R.id.editName)
        editEmail = findViewById(R.id.editEmail)
        editMobile = findViewById(R.id.editMobile)
        editAddress = findViewById(R.id.editAddress)
        editPassword = findViewById(R.id.editPassword)
        editConfirmPassword = findViewById(R.id.editConfirmPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            if(editName.text.toString()=="" || editEmail.text.toString()=="" || editMobile.text.toString()=="" || editAddress.text.toString()=="" || editPassword.text.toString()=="" || editConfirmPassword.text.toString()==""){
                Toast.makeText(this@RegisterActivity, "Empty Credentials", Toast.LENGTH_SHORT).show()
            }
            else if(editPassword.text.toString()!=editConfirmPassword.text.toString()) {
                Toast.makeText(this@RegisterActivity, "Password not matched", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this@RegisterActivity,RegisterDetailsActivity::class.java)
                intent.putExtra("name",editName.text.toString())
                intent.putExtra("email",editEmail.text.toString())
                intent.putExtra("mobile",editMobile.text.toString())
                intent.putExtra("address",editAddress.text.toString())
                intent.putExtra("password",editPassword.text.toString())
                startActivity(intent)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}