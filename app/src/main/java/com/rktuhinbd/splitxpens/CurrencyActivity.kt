package com.rktuhinbd.splitxpens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.splitxpens.databinding.ActivityCurrencyBinding

class CurrencyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurrencyBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}