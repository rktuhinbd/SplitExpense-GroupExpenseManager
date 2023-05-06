package com.rktuhinbd.splitxpens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.splitxpens.add_member.view.AddMemberActivity
import com.rktuhinbd.splitxpens.databinding.ActivityCreateGroupBinding

class CreateGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateGroupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        setupListeners()
    }

    private fun initComponents() {

        binding.toolbar.toolbarTitle.text = getString(R.string.create_group)
    }

    private fun setupListeners() {

        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }

        binding.nextBTN.setOnClickListener {
            startActivity(Intent(this, AddMemberActivity::class.java))
        }
    }
}