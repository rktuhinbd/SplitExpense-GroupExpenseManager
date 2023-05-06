package com.rktuhinbd.splitxpens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.splitxpens.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {

        binding.createGroupBtn.setOnClickListener {
            startActivity(Intent(this, CreateGroupActivity::class.java))
        }

        binding.joinGroupBtn.setOnClickListener {
            startActivity(Intent(this, JoinGroupActivity::class.java))
        }
    }
}