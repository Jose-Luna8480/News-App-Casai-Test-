package com.example.logginscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LocationPermissionsDenied : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_permissions_denied)

        val AllowAccess: Button = findViewById(R.id.button4)
        AllowAccess.setOnClickListener {
            val intent = Intent(this, NewsFeed::class.java).apply {}
            startActivity(intent)
        }
    }
}