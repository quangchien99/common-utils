package com.example.commonutils.ellipsize

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.commonutils.R
import com.example.commonutils.databinding.ActivityEllipsizeTvBinding
import com.qcp.common_utils.textview.TextViewUtils.setEllipsizedSuffix

class EllipsizeTextViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityEllipsizeTvBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTest.setOnClickListener {
            binding.edtTestText.text.toString().let {
                binding.tvResultNormal.text = it

                binding.tvResultEllipsized.text = it

                binding.tvResultEllipsized.setEllipsizedSuffix(
                    maxLines = 2,
                    suffix = "..."
                )
            }
        }

    }
}