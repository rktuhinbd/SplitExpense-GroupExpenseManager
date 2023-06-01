package com.rktuhinbd.splitxpens.get_started

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.splitxpens.create_group.view.CreateGroupActivity
import com.rktuhinbd.splitxpens.databinding.ActivityGetStartedBinding
import com.rktuhinbd.splitxpens.join_group.view.JoinGroupActivity

class GetStartedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetStartedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGetStartedBinding.inflate(layoutInflater)
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