package com.example.commonutils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvMain = findViewById<TextView>(R.id.tvMain)
//        tvMain.setEllipsizedSuffix()
    }
}