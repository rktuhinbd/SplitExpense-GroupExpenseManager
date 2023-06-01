package com.rktuhinbd.splitxpens.add_member.view

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rktuhinbd.splitxpens.R
import com.rktuhinbd.splitxpens.add_member.adapter.AddMemberAdapter
import com.rktuhinbd.splitxpens.add_member.model.MemberData
import com.rktuhinbd.splitxpens.databinding.ActivityAddMemberBinding
import com.rktuhinbd.splitxpens.utilities.Types

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

        binding.toolbar.ctaTV.text = getString(R.string.create)

        binding.toolbar.ctaTV.visibility = View.VISIBLE

        rvAdapter = AddMemberAdapter(context = this, dataList = arrayListOf())
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

        rvAdapter.onItemClick = { type: String, data: MemberData ->
            if (type == Types.Menu.rename.name) {
                Toast.makeText(this, "Rename", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
            }
        }
    }
}