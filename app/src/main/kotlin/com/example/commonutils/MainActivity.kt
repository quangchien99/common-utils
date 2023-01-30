package com.example.commonutils

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.commonutils.databinding.ActivityMainBinding
import com.example.commonutils.ellipsize.EllipsizeTextViewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEllipsize.setOnClickListener {
            startActivity(
                Intent(this, EllipsizeTextViewActivity::class.java)
            )
        }
    }
}