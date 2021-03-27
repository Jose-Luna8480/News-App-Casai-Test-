package com.example.logginscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LocationPermissions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        setContentView(R.layout.activity_location_permissions)

        val Allow: Button = findViewById(R.id.button3)
        val Deny: Button = findViewById(R.id.button2)
        val editor = sharedPreferences.edit()

        Allow.setOnClickListener {
            editor.putBoolean("LocationScreen", true)
            val intent = Intent(this, NewsFeed::class.java).apply {}
            startActivity(intent)
        }
        Deny.setOnClickListener {
            editor.putBoolean("LocationScreen", false)
            val intent = Intent(this, LocationPermissionsDenied::class.java).apply {}
            startActivity(intent)
        }
    }
}