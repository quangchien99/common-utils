package com.example.commonutils.avatar_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.commonutils.databinding.ActivityAvatarViewBinding

class AvatarViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAvatarViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnApply.setOnClickListener {
            binding.avatar.setAvatar(
                imgUrl = binding.edtUrl.text.toString(),
                name = binding.edtName.text.toString(),
                drawable = null
            )
            binding.tvFullName.text = binding.edtName.text.toString()
        }

        binding.btnApply.performClick()
    }
}