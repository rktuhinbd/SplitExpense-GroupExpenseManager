package com.rktuhinbd.splitxpens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.splitxpens.databinding.ActivityJoinGroupBinding

class JoinGroupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinGroupBinding

    var code: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJoinGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        setupListeners()
    }

    private fun initComponents() {

        binding.toolbar.toolbarTitle.text = getString(R.string.join_group)

        binding.codeET.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

                binding.codeET.error = null

                code = s.toString().trim()

                if (code.length == 6) {
                    binding.joinBTN.isEnabled = true
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun setupListeners() {

        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }
    }
}