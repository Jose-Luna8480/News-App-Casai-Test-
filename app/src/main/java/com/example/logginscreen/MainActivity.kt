package com.example.logginscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("UserName", "")
        val password = sharedPreferences.getString("Password", "")
        if (username != null && password != null) {
            if(sharedPreferences.getBoolean("LocationScreen", true)){
                val intent = Intent(this, LocationPermissions::class.java).apply {}
                startActivity(intent)
            }else{
                val intent = Intent(this, LocationPermissionsDenied::class.java).apply {}
                startActivity(intent)
            }
        }

        val logInButton: Button = findViewById(R.id.button)
        logInButton.setOnClickListener { obtainUserAndPassword() }
    }

    fun obtainUserAndPassword(){
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        val userEditText = findViewById<View>(R.id.Username) as EditText
        val userText = userEditText.text.toString()

        val passwordEditText = findViewById<View>(R.id.Password) as EditText
        val passwordText = passwordEditText.text.toString()

        if (userText.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(userText).matches()){
            Toast.makeText(this.applicationContext, "Invalid user or password", Toast.LENGTH_SHORT).show()
        }else{
            //Toast.makeText(this.applicationContext, "The user is: ${sharedPreferences.getString("UserName", "")} and the password is: ${sharedPreferences.getString("Password", "")}", Toast.LENGTH_SHORT).show()
            val editor = sharedPreferences.edit()
            editor.putString("UserName", userText)
            editor.putString("Password", passwordText)
            editor.putBoolean("LocationScreen", true)
            editor.apply()
            val intent = Intent(this, LocationPermissions::class.java).apply {}
            startActivity(intent)

        }
    }
}