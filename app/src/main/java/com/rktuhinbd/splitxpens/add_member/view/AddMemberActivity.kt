package com.rktuhinbd.splitxpens.add_member.view

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.splitxpens.R
import com.rktuhinbd.splitxpens.add_member.adapter.AddMemberAdapter
import com.rktuhinbd.splitxpens.add_member.model.MemberData
import com.rktuhinbd.splitxpens.databinding.ActivityAddMemberBinding

class AddMemberActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMemberBinding

    private lateinit var rvAdapter: AddMemberAdapter

    private var memberList: MutableList<MemberData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddMemberBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        setupListeners()
    }

    private fun initComponents() {

        binding.toolbar.toolbarTitle.text = getString(R.string.add_member)

        rvAdapter = AddMemberAdapter(arrayListOf())
        binding.rvMembers.adapter = rvAdapter
    }

    private fun setupListeners() {

        binding.toolbar.ivBack.setOnClickListener {
            finish()
        }

        binding.addMemberBTN.setOnClickListener {
            if (!TextUtils.isEmpty(binding.nameTET.text.toString().trim())) {
                memberList.add(MemberData(binding.nameTET.text.toString().trim()))
                rvAdapter.updateData(memberList)
                binding.nameTET.text?.clear()
            }
        }

        rvAdapter.onItemClick = {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
    }
}